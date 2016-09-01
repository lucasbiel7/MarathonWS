/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.util;

/**
 *
 * @author OCTI01
 */
public enum CategoriaIdade {
    U18(0, 18),
    M18U29(18, 30),
    M30U39(30, 40),
    M40U55(40, 56),
    M56U70(56, 70),
    U70(70, Integer.MAX_VALUE);

    private final int menor;
    private final int maior;

    public int getMenor() {
        return menor;
    }

    public int getMaior() {
        return maior;
    }

    private CategoriaIdade(int menor, int maior) {
        this.menor = menor;
        this.maior = maior;
    }

    public static CategoriaIdade categoria(int anos) {
        for (CategoriaIdade categoriaIdade : CategoriaIdade.values()) {
            if (anos >= categoriaIdade.getMenor() && anos < categoriaIdade.getMaior()) {
                return categoriaIdade;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.equals(U18) ? "Under 18" : this.equals(U70) ? "Over 70" : getMenor() + " to " + (getMaior()-1);
    }
}
