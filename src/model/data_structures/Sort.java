package model.data_structures;

public class Sort {
	
    private static void merge(Comparendo[] a, Comparendo[] aux, int lo, int mid, int hi, int n) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i], n)) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi, n);
    }

    private static void sort(Comparendo[] a, Comparendo[] aux, int lo, int hi,int n) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, n);
        sort(a, aux, mid + 1, hi, n);
        merge(a, aux, lo, mid, hi, n);
    }


    public static void sort(Comparendo[] a, int n) {
    	Comparendo[] aux = new Comparendo[a.length];
        sort(a, aux, 0, a.length-1, n);
    }



    private static boolean less(Comparendo v, Comparendo w, int n) {
    	
    	if(n==0){
    		return v.darCodInfeaccion().compareTo(w.darCodInfeaccion())>0;
    	}
    	else if(n==1){
    		return v.darFecha().compareTo(w.darFecha())<0;
    	}
        else{
    			
    		
    	}
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparendo[] a, int n) {
        return isSorted(a, 0, a.length - 1, n);
    }

    private static boolean isSorted(Comparendo[] a, int lo, int hi, int n) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1], n)) return false;
        return true;
    }

    private static void merge(Comparendo[] a, int[] index, int[] aux, int lo, int mid, int hi, int n) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k]; 
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]], n)) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }

    public static int[] indexSort(Comparendo[] a , int b) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;

        int[] aux = new int[n];
        sort(a, index, aux, 0, n-1, b);
        return index;
    }

    private static void sort(Comparendo[] a, int[] index, int[] aux, int lo, int hi, int n) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo, mid,n);
        sort(a, index, aux, mid + 1, hi, n);
        merge(a, index, aux, lo, mid, hi, n);
    }

  


}
