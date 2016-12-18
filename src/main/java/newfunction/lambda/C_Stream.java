package newfunction.lambda;

import demooject.Artist;

import java.util.*;

/**
 * 流：迭代器
 */
public class C_Stream {
    public static void main(String[] args) {
        List<Artist> allArtists = new ArrayList<>();
        int count = 0;

        //传统方式:for循环
        for(Artist artist : allArtists){
            if("London".equals(artist.getOrigin())){
                count++;
            }
        }

        //传统方式：迭代器
        Iterator<Artist> iterator = allArtists.iterator();
        while(iterator.hasNext()){
            Artist artist = iterator.next();
            if("London".equals(artist.getOrigin())){
                count++;
            }
        }

        //lambda：内部迭代
        long count2 = allArtists.stream()
                .filter(artist -> "London".equals(artist.getOrigin()))
                .count();
    }
}
