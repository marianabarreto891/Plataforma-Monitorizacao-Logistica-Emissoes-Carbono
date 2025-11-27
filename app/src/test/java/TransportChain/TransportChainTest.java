package TransportChain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransportChainTest {

    private TransportChain chain;

    @BeforeEach
    void setUp() {
        chain = new TransportChain(new ArrayList<>());
    }

    @Test
    void testAddTransportOperation() {
        TransportOperation op = new TransportOperation(
                "TO001", "CHAIN001", "PT", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);

        chain.addElement(op);

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().contains(op));
    }

    @Test
    void testAddNullElement() {
        int initialSize = chain.getElements().size();

        chain.addElement(null);

        assertEquals(initialSize, chain.getElements().size(), "A lista não deve alterar quando adicionamos null");
        assertTrue(chain.getElements().isEmpty(), "A lista deve continuar vazia");
    }

    @Test
    void testAddHubOperation() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 100.0, 0.5);

        chain.addElement(ho);

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().contains(ho));
    }

    @Test
    void testRemoveTransportOperation() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);
        chain.addElement(to);

        boolean removed = chain.removeElement(to);

        assertTrue(removed, "O método removeElement deve retornar true");
        assertFalse(chain.getElements().contains(to), "A lista não deve conter o TransportOperation removido");
        assertEquals(0, chain.getElements().size(), "A lista deve ficar vazia após remoção");
    }

    @Test
    void testRemoveNonExistentTransportOperation() {
        TransportOperation toInexistente = new TransportOperation(
                "TO999", "CHAIN001", "PT", 200.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);

        boolean removed = chain.removeElement(toInexistente);

        assertFalse(removed, "O método removeElement deve retornar false para elementos inexistentes");
        assertTrue(chain.getElements().isEmpty(), "A lista deve permanecer inalterada");
    }

    @Test
    void testRemoveExistingHubOperation() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 100.0, 0.5);
        chain.addElement(ho);

        boolean removed = chain.removeElement(ho);

        assertTrue(removed, "O método removeElement deve retornar true para HubOperation existente");
        assertFalse(chain.getElements().contains(ho), "A lista não deve conter o HubOperation removido");
        assertEquals(0, chain.getElements().size(), "A lista deve ficar vazia após remoção");
    }

    @Test
    void testRemoveNonExistentHubOperation() {
        HubOperation hoInexistente = new HubOperation("HO999", "CHAIN001", "PT", 200.0, 0.5);

        boolean removed = chain.removeElement(hoInexistente);

        assertFalse(removed, "O método removeElement deve retornar false para HubOperation inexistente");
        assertTrue(chain.getElements().isEmpty(), "A lista deve permanecer inalterada");
    }

    @Test
    void testEditarHubOperation_Sucesso() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 0.5, 100.0);
        chain.addElement(ho);

        boolean resultado = chain.editarHubOperation("HO001", 20.0, 10.0);

        assertTrue(resultado, "Deve retornar true ao editar um HubOperation existente");
        assertEquals(10.0, ho.getConsumoEnergia(), 0.001, "O consumo de energia deve ser atualizado");
        assertEquals(20.0, ho.getFatorEmissao(), 0.001, "O fator de emissão deve ser atualizado");
    }

    @Test
    void testEditarHubOperation_Falha_IDInexistente() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 0.5, 100.0);
        chain.addElement(ho);

        boolean resultado = chain.editarHubOperation("HO999", 10.0, 20.0);

        assertFalse(resultado, "Deve retornar false ao tentar editar um HubOperation inexistente");
        assertEquals(100.0, ho.getConsumoEnergia(), 0.001, "O consumo de energia não deve mudar");
        assertEquals(0.5, ho.getFatorEmissao(), 0.001, "O fator de emissão não deve mudar");
    }

    @Test
    void testEditarTransportOperation_Falha_ValorNegativo() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);

        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO001", -10, 50, 0.8, 0.1, 5.0, 0.2, 0.1);

        assertFalse(resultado, "Deve retornar false ao tentar editar com valor negativo");
    }

    @Test
    void testEditarTransportOperation_Sucesso_ValoresPositivos() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);
        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO001", 120.0, 60.0, 1.0, 0.2, 6.0, 0.3, 0.2);

        assertTrue(resultado, "Deve retornar true ao editar com valores válidos");
    }

    @Test
    void testEditarTransportOperation_Falha_IDInexistente() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);
        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO999", 200.0, 100.0, 0.5, 0.1, 10.0, 0.2, 0.1);

        assertFalse(resultado, "Deve retornar false ao tentar editar um TransportOperation inexistente");
    }

    @Test
    void testEditarHubOperation_Sucesso_ValoresZero() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 10.0, 20.0);
        chain.addElement(ho);

        boolean resultado = chain.editarHubOperation("HO001", 0.0, 0.0);

        assertTrue(resultado, "Deve retornar true ao editar um HubOperation existente");
    }

    @Test
    void testEditarHubOperation_Sucesso_ValoresNormais() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 10.0, 20.0);
        chain.addElement(ho);

        boolean resultado = chain.editarHubOperation("HO001", 0.5, 100.0);

        assertTrue(resultado, "Deve retornar true ao editar um HubOperation existente");

        assertEquals(100.0, ho.getConsumoEnergia(), 0.001, "O consumo de energia deve ser atualizado");
        assertEquals(0.5, ho.getFatorEmissao(), 0.001, "O fator de emissão deve ser atualizado");
    }

    @Test
    void testEditarHubOperation_Sucesso_ValoresMaximos() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 10.0, 20.0);
        chain.addElement(ho);

        boolean resultado = chain.editarHubOperation("HO001", 1000, 1000000);

        assertTrue(resultado, "Deve retornar true ao editar um HubOperation existente com valores altos");

        assertEquals(1000000, ho.getConsumoEnergia(), 0.001, "O consumo de energia deve ser atualizado para valor alto");
        assertEquals(1000, ho.getFatorEmissao(), 0.001, "O fator de emissão deve ser atualizado para valor alto");
    }

    @Test
    void testEditarTransportOperation_ValoresZerados() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT",
                100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1);
        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO001", 0, 0, 0, 0, 0, 0, 0);

        assertTrue(resultado, "Deve retornar true ao editar um TO existente com todos os valores = 0");
    }

    @Test
    void testEditarTransportOperation_ValoresTipicos() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT",
                1000.0, 500.0, 10.0, 5.0, 50.0, 2.0, 1.0);
        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO001", 100, 50, 0.5, 50, 100, 0.5, 0.5);

        assertTrue(resultado, "Deve retornar true ao editar um TO existente com valores típicos");
    }

    @Test
    void testEditarTransportOperation_ValoresLimiteSuperior() {
        TransportOperation to = new TransportOperation("TO001", "CHAIN001", "PT",
                1000.0, 500.0, 10.0, 5.0, 50.0, 2.0, 1.0);
        chain.addElement(to);

        boolean resultado = chain.editarTransportOperation("TO001", 10_000, 1000, 1, 500, 10_000, 1000, 1000);

        assertTrue(resultado, "Deve retornar true ao editar um TO existente com valores no limite superior");
    }
}


