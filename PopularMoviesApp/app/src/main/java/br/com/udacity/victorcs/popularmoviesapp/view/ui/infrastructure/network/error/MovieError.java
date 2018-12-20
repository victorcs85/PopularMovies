package br.com.udacity.victorcs.popularmoviesapp.view.ui.infrastructure.network.error;

public class MovieError extends Exception {
    private String message;

    public MovieError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
