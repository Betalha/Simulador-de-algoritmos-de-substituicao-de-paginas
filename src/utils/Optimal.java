package utils;

import java.util.*;

public class Optimal {
    private Integer[] pages;
    private Integer frames;
    private int pageFaults;
    private Set<Integer> memory;

    public Optimal(Integer[] pages, Integer frames) {
        this.pages = pages;
        this.frames = frames;
        this.pageFaults = 0;
        this.memory = new LinkedHashSet<>();
    }

    public Integer simulate() {
        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];
            if (!memory.contains(currentPage)) {
                pageFaults++;
                if (memory.size() < frames) {
                    memory.add(currentPage);
                } else {
                    Integer pageToReplace = null;
                    int farthestNextUse = -1;

                    for (Integer pageInMem : memory) {
                        int nextUse = findNextUse(i + 1, pageInMem);
                        if (nextUse == -1) {
                            pageToReplace = pageInMem;
                            break;
                        }
                        if (nextUse > farthestNextUse) {
                            farthestNextUse = nextUse;
                            pageToReplace = pageInMem;
                        }
                    }
                    if (pageToReplace != null) {
                        memory.remove(pageToReplace);
                    }
                    memory.add(currentPage);
                }
            }
        }
        return pageFaults;
    }

    private int findNextUse(int startIndex, int page) {
        for (int i = startIndex; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return -1; // nunca mais usada
    }
}