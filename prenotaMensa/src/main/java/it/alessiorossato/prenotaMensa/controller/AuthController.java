package it.alessiorossato.prenotaMensa.controller;


import it.alessiorossato.prenotaMensa.log.LogUtente;
import it.alessiorossato.prenotaMensa.repository.UserRepository;
import it.alessiorossato.prenotaMensa.security.jwt.JwtTokenRequest;
import it.alessiorossato.prenotaMensa.security.jwt.JwtTokenResponse;
import it.alessiorossato.prenotaMensa.security.jwt.JwtUtils;
import it.alessiorossato.prenotaMensa.security.services.UserDetailsServiceImpl;
import it.alessiorossato.prenotaMensa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
/*
    @Autowired
    RoleRepository roleRepository;*/

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private HttpServletRequest request;

//	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final LogUtente logger = new LogUtente(AuthController.class);

/*
    @RequestMapping(value = "/tutti", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<it.alessiorossato.sagriamo.bean.User>> listaUtenti() {
        //List<User> all = userRepository.findAll();

        logger.info("Recupero la lista utenti");
        List<User> all = userService.getAll();
        Set<it.alessiorossato.sagriamo.bean.User> convertiUtente = ConvertModelBean.convertiUtente(all);
        logger.info("Restituisco la lista utenti " + convertiUtente);
        return new ResponseEntity<Set<it.alessiorossato.sagriamo.bean.User>>(convertiUtente, HttpStatus.OK);
    }
*/

	/*@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
*/

    @PostMapping(value = "/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException, UnknownHostException {
        logger.info("******  Autenticazione e Generazione Token   ******" + authenticationRequest.getUsername());

        String remoteAddr = request.getRemoteAddr();
        String serverName = request.getServerName();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getHostName();


        //logger.info("REMOTE HOST: "+request.getRemoteHost());
        logger.info("request.getRemoteAddr() " + remoteAddr);
        logger.info("request.getServerName() " + serverName);
        logger.info("InetAddress.getLocalHost().getHostAddress() " + hostAddress);
        logger.info("InetAddress.getLocalHost().getHostName() " + hostName);

        // Remote address
        //logger.info("InetAddress.getLoopbackAddress().getHostAddress()"+InetAddress.getLoopbackAddress().getHostAddress());
        //logger.info("InetAddress.getLoopbackAddress().getHostName() "+InetAddress.getLoopbackAddress().getHostName());

        System.out.println("Utente " + authenticationRequest.getUsername().trim());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println("User details " + userDetails);
        final String token = jwtUtils.generateToken(userDetails);

        logger.warn(String.format("Token %s", token));

        userService.utenteAutenticato(authenticationRequest.getUsername(), LocalDateTime.now(), remoteAddr, serverName, hostAddress, hostName);
        return ok(new JwtTokenResponse(token));
    }


    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }


    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            logger.warn("UTENTE DISABILITATO");
            throw new it.alessiorossato.prenotaMensa.exception.AuthenticationException("UTENTE DISABILITATO", e);
        } catch (BadCredentialsException e) {
            logger.warn("CREDENZIALI NON VALIDE");
            throw new it.alessiorossato.prenotaMensa.exception.AuthenticationException("CREDENZIALI NON VALIDE", e);
        }
    }

/*
    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody it.alessiorossato.sagriamo.bean.User signUpRequest) throws NotFoundException {

        logger.info("Aggiorno un utente " + signUpRequest.getUsername());

        Optional<User> byUsername = userRepository.findById(signUpRequest.getId());
        if (byUsername.isPresent()) {

            Optional<User> userRepositoryByUsername = userRepository.findByUsername(signUpRequest.getUsername());
            if (userRepositoryByUsername.isPresent() && userRepositoryByUsername.get().getId() != signUpRequest.getId()) {
                return badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }


            User user = byUsername.get();
            user.setEnable(signUpRequest.isEnable());
            user.setUsername(signUpRequest.getUsername());


            //la password è già presa dal DB

            Set<String> strRoles = signUpRequest.getRoles();
            Set<Ruolo> roles = new HashSet<>();

            if (strRoles == null) {

            } else {
                strRoles.forEach(role -> {

                    System.out.println("Ruolo ricevuto " + role);

                    Ruolo adminRole = roleRepository.findByNome(role)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);


                });
            }

            user.setRoles(roles);
            userRepository.save(user);

            logger.info("Utente aggiornato");
            return ok()
                    .headers(HeaderUtil.createEntityUpdateAlert("Auth", ""))
                    .body(ConvertModelBean.convertiUtente(user));
        }


        //return ResponseEntity.badRequest();//(new MessageResponse("User Update successfully!"));

        throw new NotFoundException("Errore");


    }*/

    //@PostMapping("/updatepassword")
  /*  @RequestMapping(value = "/updatepassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updatePasswordUser(@Valid @RequestBody it.alessiorossato.sagriamo.bean.Password userPassword) throws NotFoundException, UserNotFoundException {
        logger.info("Aggiorno password " + userPassword.getIdUtente());
        userService.modificaPassword(userPassword.getIdUtente(), userPassword.getPassword());
        logger.info("Password aggiornata");
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
