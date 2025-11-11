package com.github.souzafcharles.api.util;

public class RuralPropertyMessages {

    private RuralPropertyMessages() {
        throw new IllegalStateException("Utility class");
    }

    // Entity messages
    public static final String PROPERTY_NOT_FOUND = "Rural property not found";
    public static final String PROPERTY_DELETED_SUCCESSFULLY = "Rural property deleted successfully.";

    // Validation messages
    public static final String PROPERTY_NAME_REQUIRED = "Property name is required";
    public static final String PROPERTY_NAME_SIZE_EXCEEDED = "Property name must not exceed 255 characters";
    public static final String LATITUDE_RANGE_INVALID = "Latitude must be between -90 and 90";
    public static final String LONGITUDE_RANGE_INVALID = "Longitude must be between -180 and 180";
    public static final String AREA_POSITIVE_REQUIRED = "Area must be positive";

    // Error messages
    public static final String INTERNAL_ERROR_OCCURRED = "An internal error occurred";
    public static final String VALIDATION_ERROR = "Validation error";
    public static final String DATABASE_ERROR = "Database error occurred";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred";

    // OpenAPI
    public static final String OPENAPI_TITLE = "Rural Property GeoData API";
    public static final String OPENAPI_DESCRIPTION = "A Spring Boot backend for registering and listing rural properties with geographic data stored in PostgreSQL";

    // OpenAPI Operation Summaries
    public static final String SUMMARY_CREATE_PROPERTY = "Create a new rural property";
    public static final String SUMMARY_GET_ALL_PROPERTIES = "Get all rural properties";
    public static final String SUMMARY_GET_PROPERTY_BY_ID = "Get rural property by ID";
    public static final String SUMMARY_UPDATE_PROPERTY = "Update an existing rural property";
    public static final String SUMMARY_DELETE_PROPERTY = "Delete a rural property";

    // OpenAPI Operation Descriptions
    public static final String DESCRIPTION_CREATE_PROPERTY = "Creates a new rural property with geographic coordinates and area information. Validates input data including coordinate ranges and positive area values.";
    public static final String DESCRIPTION_GET_ALL_PROPERTIES = "Retrieves a list of all registered rural properties with their complete geographic and area data.";
    public static final String DESCRIPTION_GET_PROPERTY_BY_ID = "Retrieves a specific rural property by its unique identifier. Returns 404 if property not found.";
    public static final String DESCRIPTION_UPDATE_PROPERTY = "Updates an existing rural property's information. All fields are validated and the property must exist.";
    public static final String DESCRIPTION_DELETE_PROPERTY = "Deletes a rural property by its ID. Returns success message or 404 if property not found.";
}