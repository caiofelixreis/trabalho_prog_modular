package com.example.hrpayroll.service;

import com.example.hrpayroll.model.FuncionarioModel;
import com.example.hrpayroll.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
        @Autowired
        private final FuncionarioRepository funcionarioRepository;

        public FuncionarioService(FuncionarioRepository funcionarioRepository) {
                this.funcionarioRepository = funcionarioRepository;
        }

        public FuncionarioModel create(FuncionarioModel funcionarioModel) {
                return funcionarioRepository.save(funcionarioModel);
        }

        public List<FuncionarioModel> list() {
                return funcionarioRepository.findAll();
        }

        public FuncionarioModel findOneById(Long id) {
                Optional<FuncionarioModel> userOptional = funcionarioRepository.findById(id);

                if (userOptional.isPresent()) {
                    FuncionarioModel user = userOptional.get();
                    return user;
                }

                return null;
        }


        public Double getSalarioPorHoraById(Long id) {
            Optional<FuncionarioModel> user = funcionarioRepository.findById(id);

            if (user.isPresent()) {
                FuncionarioModel funcionarioModel = user.get();
                Double salarioBruto = funcionarioModel.getSalarioBruto();
                Integer horasTrabalhadas = funcionarioModel.getHorasTrabalhadasPorDia();
                Double salarioPorHora = (salarioBruto / 30) / horasTrabalhadas;
                return salarioPorHora;
            }
            return null;
        }
}
