package com.tim3.UserService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        ArrayList<User> allUsers = new ArrayList<User>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public User getUserById(Integer id){

        Optional<User> optionalUser = UserRepository.findById(id);
        if(!optionalUser.isPresent()){
            return null;
        }
        return optionalUser.get();
    }

    public User create(String name, String email){
        User user = new User(name, email);
        return userRepository.save(user);
    }

    public User getByName(String name){
        for (User user : muserRepository.findAll()) {
            if (user.getName().compareTo(name) == 0) {
                return user;
            }
        }
        return null;
    }

    public Boolean deleteUserById(Integer id){
        if(!UserRepository.existsById(id)){
            return false;
        }
        UserRepository.deleteUserById(id);
        return true;
    }
}