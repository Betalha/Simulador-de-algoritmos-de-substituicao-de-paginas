package utils;

import java.util.*;

public class NFU {
    private Integer[] pages;
    private Integer frames;
    private Integer pageFaults;
    private Map<Integer,Integer> map;
    private LinkedList<Integer> memory;

    public NFU(Integer[] pages, Integer frames) {
        this.map = new HashMap<>();
        this.pages = pages;
        this.frames = frames;
        this.pageFaults = 0;
        this.memory = new LinkedList<>();
    }
    public Integer simulate(){
        for(Integer page : this.pages){
            map.put(page,map.getOrDefault(page,0)+1);
            if(!memory.contains(page)){
                this.pageFaults++;
                if(memory.size() < this.frames){ memory.add(page); }
                else {
                    Integer lowest = Integer.MAX_VALUE;
                    Integer lowestKey = null;
                    for(Integer key : memory){
                        if(lowest > map.get(key)){
                            lowest = map.get(key);
                            lowestKey = key;
                        }
                    }
                    map.remove(lowestKey);
                    memory.remove(lowestKey);
                    memory.add(page);
                }
            }
        }
        return this.pageFaults;
    }
}


