package com.example.hrpayroll.Service;

import com.example.hrpayroll.DTO.UserDTO;
import com.example.hrpayroll.Model.UserModel;
import com.example.hrpayroll.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        @Autowired
        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        public UserModel create(UserModel userModel) {
                return userRepository.save(userModel);
        }

        public List<UserModel> list() {
                return userRepository.findAll();
        }

        public Optional<UserModel> findOneById(String id) {
                return userRepository.findById(id);
        }
        public UserModel updateUser (String id, UserDTO newUserData) {
                Optional<UserModel> user = userRepository.findById(id);
                
                user.setNome(newUserData.getNome());
                user.setEmail(newUserData.getEmail());
                final S save = userRepository.save(newUserData);
                return save;
                
        }
        public List<UserModel> delete(String id){
                userRepository.findById(id);
                return userRepository.findAll();
        }


}
