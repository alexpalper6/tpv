package modeloJuego;

public enum Generos {
    /**
     * Tipos de géneros
     */
    RPG("Juegos de Rol"),
    MMORPG("Rol multijugador masivo"),
    FPS("Shooter primera persona"),
    AVENTURAS("Aventuras"),
    CASUAL("Casual"),
    PLATAFORMAS("Plataformas"),
    TODOS("Todos");

    /**
     * Descripción del género, la información que sale en los botones del género.
     */
    private String descripcion;

    /**
     * Constructor de Generos.
     * @param descirpcion
     */
    Generos(String descirpcion) {
        this.descripcion = descirpcion;
    }

    /**
     * Obtiene la descripción.
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

}
