package edu.eci.arsw.blueprints;

public record ApiResponse<T>(int code, String message, T data) {

    public static <T> ApiResponse<T> ok(String m, T d) {return new ApiResponse<>(200, m, d);}

    public static <T> ApiResponse<T> created(String m, T d) {return new ApiResponse<>(201, m, d);}

    public static <T> ApiResponse<T> accepted(String m, T d) {return new ApiResponse<>(202, m, d);}

    public static <T> ApiResponse<T> badRequest(String m, T d) {return new ApiResponse<>(400, m, d);}

    public static <T> ApiResponse<T> notFound(String m, T d) {return new ApiResponse<>(404, m, d);}
}
