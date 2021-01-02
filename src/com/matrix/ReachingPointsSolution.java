package com.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author boyuangong created on 6/18/20 at 00:41
 */
public class ReachingPointsSolution {
    private int tx;
    private int ty;
    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }
    }

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        this.tx = tx;
        this.ty = ty;
        // bfs
        Queue<Point> q = new LinkedList<>();
        if (sx == tx && sy == ty) {
            return true;
        }
        List<Point> searched = new ArrayList<>();
        q.add(new Point(sx, sy));
        while (!q.isEmpty()) {
            System.out.println(q.toString());
            Point cur = q.poll();
            searched.add(cur);
            Point child1 = new Point(cur.x+cur.y, cur.y);

            if (isTarget(child1)) {
                return true;
            }

            if (!searched.contains(child1) && child1.x <= tx && child1.y <= ty) {
                q.add(child1);
            }

            Point child2 =  new Point(cur.x, cur.x+cur.y);
            if (isTarget(child2)) {
                return true;
            }

            if (!searched.contains(child2) && child2.x <= tx && child2.y <= ty) {
                q.add(child2);
            }
        }
        return false;
    }

    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        while (sx <= tx && sy <= ty) {
            System.out.println(tx);
            System.out.println(ty);
            if (tx > ty) {
                if (ty <= sy) {
                    return (tx - sx) % ty == 0;
                } else {
                    tx = tx % ty;
                }
            } else {
                if (tx <= sx) {
                    return (ty - sy) % tx == 0;
                } else {
                    ty = ty % tx;
                }
            }
        }
        return false;
    }


    private boolean isTarget(Point p) {
        return p.x == tx && p.y == ty;
    }

    public static void main(String[] args) {
        ReachingPointsSolution solution = new ReachingPointsSolution();
        int x1 = 10;
        int x2 = 4;
        int y1 = 10;
        int y2 = 20;
        System.out.println(solution.reachingPoints(x1,x2,y1,y2));
        System.out.println(solution.reachingPoints2(x1,x2,y1,y2));
    }
}
