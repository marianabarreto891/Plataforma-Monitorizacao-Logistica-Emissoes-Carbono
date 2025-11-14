package cargo;

/**
 * Representa os tipos de elementos utilizados no sistema.
 *
 * Cada tipo possui um código associado, definido no próprio enum.
 */
public enum ElementType {

    /**
     * Tipo que representa um hub.
     * Código associado: "HO".
     */
    HUB("HO"),

    /**
     * Tipo que representa um meio de transporte.
     * Código associado: "TO".
     */
    TRANSPORT("TO");

    /** Código identificador do tipo de elemento. */
    private final String code;

    /**
     * Construtor do enum.
     *
     * @param code código associado ao tipo
     */
    ElementType(String code) {
        this.code = code;
    }

    /**
     * Obtém o código associado ao tipo de elemento.
     *
     * @return código do tipo
     */
    public String getCode() {
        return code;
    }
}
