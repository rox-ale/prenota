package it.alessiorossato.prenotaMensa.security.services;

import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String ErrMsg = "";

		if (username == null || username.length() < 2)
		{
			ErrMsg = "Nome utente assente o non valido";

			logger.warn(ErrMsg);

			throw new UsernameNotFoundException(ErrMsg);
		}


		Utente user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));


		org.springframework.security.core.userdetails.User.UserBuilder builder = null;
		builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
		builder.disabled((user.isEnable() ? false : true));
		builder.password(user.getPassword());

		String[] profili = new String [1];
		profili[0]=user.getRuolo().getNome();

		builder.authorities(profili);

		return builder.build();
		//return UserDetailsImpl.build(user);
	}

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

/*
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		String ErrMsg = "";

		if (username == null || username.length() < 2)
		{
			ErrMsg = "Nome utente assente o non valido";

			logger.warn(ErrMsg);

			throw new UsernameNotFoundException(ErrMsg);
		}

		Optional<User> utente =  userRepository.findByUsername(username);

		if (utente == null || !utente.isPresent())
		{
			ErrMsg = String.format("Utente %s non Trovato!!", username);

			logger.warn(ErrMsg);

			throw new UsernameNotFoundException(ErrMsg);
		}

		User user=utente.get();
		org.springframework.security.core.userdetails.User.UserBuilder builder = null;
		builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
		builder.disabled((user.isEnable() ? false : true));
		builder.password(user.getPassword());

		String[] profili = user.getRoles()
				.stream().map(a -> "" + a.getName()).toArray(String[]::new);

		builder.authorities(profili);

		return builder.build();


	}
*/
}
