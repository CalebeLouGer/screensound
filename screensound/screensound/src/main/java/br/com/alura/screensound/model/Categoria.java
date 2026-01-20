package br.com.alura.screensound.model;

public enum Categoria {
    ROCK("Rock"),
    GOSPEL("Gospel"),
    PAGODE("Pagode"),
    SERTANEJO("Sertanejo");


    private String categoriaDigitada;

    Categoria(String categoriaDigitada) {
        this.categoriaDigitada = categoriaDigitada;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaDigitada.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
