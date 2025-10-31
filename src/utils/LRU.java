package utils;

import java.util.LinkedList;

public class LRU {
    private Integer[] pages;
    private Integer frames;
    private Integer pageFaults;
    private LinkedList<Integer> used;
    public LRU(Integer[] pages, Integer frames) {
        this.pages = pages;
        this.frames = frames;
        this.pageFaults = 0;
        this.used = new LinkedList<>();
    }

    public Integer simulate() {
        for(Integer p : this.pages){
            if(!this.contains(p)){
                this.pageFaults++;
                if(this.used.size() >= this.frames){
                    Integer remove = this.used.removeFirst();
                    this.used.addLast(p);

                }else {
                    this.used.addLast(p);
                }
            }else {
                int index = this.used.indexOf(p);
                this.used.remove(index);
                this.used.addLast(p);
            }
        }
        return pageFaults;
    }

    public boolean contains(Integer p){
        for(Integer i : this.used){
            if (i == p){return true;}
        }
        return  false;
    }
}
