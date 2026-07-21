package it.unisa.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Carrello {

    private ArrayList<ItemCarrello> prodotti;

    public Carrello() {
        prodotti = new ArrayList<>();
    }

    public void addProdotto(ProdottoBean prodotto) {
        for (ItemCarrello item : prodotti) {
            if (item.getId() == prodotto.getIdProdotto()) {
                item.incrementa();
                return;
            }
        }

        ItemCarrello item = new ItemCarrello(prodotto);
        prodotti.add(item);
    }

    public void deleteProdotto(ProdottoBean prodotto) {
        Iterator<ItemCarrello> iterator = prodotti.iterator();

        while (iterator.hasNext()) {
            ItemCarrello item = iterator.next();

            if (item.getId() == prodotto.getIdProdotto()) {
                iterator.remove();
                return;
            }
        }
    }

    public ItemCarrello getItem(int id) {
        for (ItemCarrello item : prodotti) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public double calcolaCosto() {
        double totale = 0;

        for (ItemCarrello item : prodotti) {
            totale += item.getTotalPrice();
        }

        return totale;
    }

    public boolean isEmpty() {
        return prodotti.isEmpty();
    }

    public ArrayList<ItemCarrello> getProdotti() {
        return prodotti;
    }

    public void svuota() {
        prodotti.clear();
    }

    public int size() {
        return prodotti.size();
    }

    public ItemCarrello get(int index) {
        return prodotti.get(index);
    }
}