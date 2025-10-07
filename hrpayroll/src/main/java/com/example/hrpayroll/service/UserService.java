package com.example.hrpayroll.service;

import com.example.hrpayroll.model.UserModel;
import com.example.hrpayroll.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        public UserModel findOneById(Long id) {
                Optional<UserModel> userOptional = userRepository.findById(id);

                if (userOptional.isPresent()) {
                    UserModel user = userOptional.get();
                    return user;
                }

                return null;
        }


        public Double getSalarioPorHoraById(Long id) {
            Optional<UserModel> user = userRepository.findById(id);

            if (user.isPresent()) {
                UserModel userModel = user.get();
                Double salarioBruto = userModel.getSalarioBruto();
                Integer horasTrabalhadas = userModel.getHorasTrabalhadasPorDia();
                Double salarioPorHora = (salarioBruto / 30) / horasTrabalhadas;
                return salarioPorHora;
            }
            return null;
        }
}
