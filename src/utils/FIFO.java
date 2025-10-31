package utils;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

    private Queue<Integer> queue;
    private Integer[] pages;
    private Integer frames;
    private Integer pageFaults;

    public FIFO(Integer[] pages, Integer frames) {
        this.pages = pages;
        this.frames = frames;
        this.pageFaults = 0;
        this.queue = new LinkedList<>();

    }

    public Integer simulate(){
        for (int page : pages){
            if(!this.contains(page)){
                pageFaults++;
                if(this.queue.size() >= this.frames){
                    Integer remove = this.queue.poll();
                    this.queue.add(page);
                }else {
                    this.queue.add(page);
                }
            }
        }
        return this.pageFaults;
    }

    public boolean contains(Integer p){
        for(Integer i : this.queue){
            if (i == p){return true;}
        }
        return  false;
    }
}
