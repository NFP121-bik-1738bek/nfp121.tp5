package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    public boolean add(T t) {
        // done - question1-1
        if(t == null)
            return false;

        if(!table.contains(t))
            return table.add(t);
        
        return false;

        //    this.add(...) déclenche cette méthode
        /*    this.contains(...) déclenche la méthode contains(...) de la class abstraite AbstractCollection
            qui est la super-classe de la super-classe (AbstractSet) de cette classe (Ensemble). */
        /*  this.addAll(...) ajoute les éléments d'une collection dans une autre collection (celle qui appelle la méthode)
            Pour faire cela, elle appelle la méthode add(...) pour chaque élément de la liste en paramètre,
            elle vérifie alors si l'élément en question existe dans la collection principale ou non.
            Si non, elle l'ajoute à la liste principale
            Finalement, elle retourne true s'il y avait une modification*, et false si non (=> si liste2 existe dans liste1)
                *c.a.d si ella a ajouté un nouvel élément qui n'existait pas déjà dans la liste principale
            */
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        // done - question1-3

        Ensemble<T> union = new Ensemble<T>();
        union.addAll(this);

        if (e == null || e.size() == 0) { 
            return union;
        }
        
        union.addAll(e);
        
        return union;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        // done - question1-3

        Ensemble<T> inter = new Ensemble<T>();
        
        if (e == null || e.size() == 0) {
            return inter;
        }
        
        inter.addAll(this);
        inter.retainAll(e);
        
        return inter;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        // done - question1-3

        Ensemble<T> diff = new Ensemble<T>();
        diff.addAll(this);

        if (e == null || e.size() == 0) {
            return diff;
        }

        diff.removeAll(e);
        
        return diff;
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) {
        // done - question1-3

        Ensemble<T> diff_sym = new Ensemble<T>();
        diff_sym.addAll(this);

        if (e == null || e.size() == 0) {
            return diff_sym;
        }
        
        diff_sym = diff_sym.union(e);
        diff_sym.removeAll(this.inter(e));
            
        return diff_sym;
    }
    
}
