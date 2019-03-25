package com.tim3.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(path = "/{id}")
    private ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping
    private ResponseEntity<Muser> getUserByName(@RequestParam String name){
        User user = userService.getByName(name);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.create(user.getName(), user.getEmail()), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    private ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}