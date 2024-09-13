package ar.com.unq.eis.trainup.controller.dto

class ErrorDTO(e: Exception) {
    var msg: String? = e.message;
}