package br.com.fiap.mspedidos.domain.exception;

public class ApiInternalError extends RuntimeException{

        public ApiInternalError(String message) {
            super(message);
        }

}
