package org.incorp.i;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SIZE = 3;                              // Размер поля
    static final char HUMAN = 'X';                          // Чем ходит человек
    static final char PC = 'O';                             // Чем ходит ПК
    static final char EMPTY = '.';                          // Чем заполняется поле
    static char[][] map;                                    // Массив карты
    static Scanner sc = new Scanner(System.in);             // Считываем введенные координаты
    static Random random = new Random();                    // Для хода глупого ПК

    public static void main(String[] args) {

        initMap();
        printMap();
        while (true) {
            humanStep();
            printMap();
            if (shekWin(HUMAN)) {
                System.out.println("Человек победил");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            pcStep();
            printMap();
            if (shekWin(PC)) {
                System.out.println("Комп победил");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }


    }

    // Заполняем карту
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int mStep1 = 0; mStep1 < SIZE; mStep1++) {
            for (int mstep2 = 0; mstep2 < SIZE; mstep2++) {
                map[mStep1][mstep2] = EMPTY;
            }
        }
    }

    //Рисуем карту
    public static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("  " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c  ", map[i][j]);
            }
            System.out.println();
        }
    }

// вводим координаты + проверка координат
    public static void humanStep() {

        int x, y;
        do {
            System.out.println("Введите координаты по горизонтали и по вертикали");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isValid(y, x));

        map[y][x] = HUMAN;
    }

    //Ход комп
    public static void pcStep() {
        int x, y;
        do {
            System.out.println("Введите координаты по горизонтали и по вертикали");
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isValid(y, x));

        map[y][x] = PC;
    }

    // проверко от дурака
    public static boolean isValid(int y, int x) {
        if (x >= SIZE || y >= SIZE || x < 0 || y < 0) {
            return false;
        }
        return map[y][x] == EMPTY;
    }

    // проверка на заполненность поял НИЧЬЯ
    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == EMPTY) {
                    return false;
                }
            }

        }
        return true;
    }

    // Условия победы
    public static boolean shekWin(char c) {
        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {
            return true;
        }
        if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {
            return true;
        }
        if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {
            return true;
        }
        if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {
            return true;
        }
        if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {
            return true;
        }
        if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {
            return true;
        }
        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
            return true;
        }
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {
            return true;
        }
        return false;
    }
}


