package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.ListIterator;
import java.util.Stack;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;

    private Originator<List<String>> originator = new Originator<List<String>>();
    private CareTaker<List<String>> careTaker = new CareTaker<List<String>>();

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);

        if (liste != null && occurrences != null) {
            afficheur.setText(liste.getClass().getName() + " et " + occurrences.getClass().getName());
            texte.setText(liste.toString());
        } else {
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        //
        boutonRetirer.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonOccurrences.addActionListener(this);
        boutonAnnuler.addActionListener(this);
        // done
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : " + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
                afficheur.setText("résultat du retrait de tous les éléments commençant par -->  " + saisie.getText()
                        + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == ordreCroissant)
            Collections.sort(liste); // done
        else if (ie.getSource() == ordreDecroissant)
            Collections.sort(liste, new sortingDec()); // done

        texte.setText(liste.toString());
    }

    private class sortingDec implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        //
        if (prefixe != null) {
            ListIterator<String> listIterator = liste.listIterator();
            while (listIterator.hasNext()) {
                String next = listIterator.next();
                if (next.length() >= prefixe.length() && next.substring(0, prefixe.length()).equals(prefixe)) {
                    listIterator.remove();
                    resultat = true;
                    occurrences.put(next, occurrences.get(next) - 1);
                }
            }
        }
        if (resultat) {
            careTaker.add(originator.saveStateToMemento());
            originator.setState(new LinkedList<String>(liste));

        }
        // done
        return resultat;
    }

    private class Memento<T> {
        private T state;

        public Memento(T state) {
            this.state = state;
        }

        public T getState() {
            return this.state;
        }
    }

    private class CareTaker<T> {
        private Stack<Memento<T>> mementoList;

        CareTaker() {
            mementoList = new Stack<Memento<T>>();
        }

        public void add(Memento<T> state) {
            mementoList.add(state);
        }

        public Memento<T> get() {
            if (mementoList.isEmpty())
                return null;
            return mementoList.pop();
        }

    }

    private class Originator<T> {
        private T state;

        public void setState(T state) {
            this.state = state;
        }

        public T getState() {
            return state;
        }

        public Memento<T> saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento<T> memento) {
            state = memento.getState();
        }
    }

}