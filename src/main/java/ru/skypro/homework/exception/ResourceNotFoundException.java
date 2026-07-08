package ru.skypro.homework.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, Object id) {
        super(resourceType + " with ID " + id + " not found.");
    }
}