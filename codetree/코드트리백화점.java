package codetree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 코드트리백화점 {
    static int N, K, X;
    static Customer[] customers;
    static PriorityQueue<CheckoutCounter> pq;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        customers = new Customer[N];
        pq = new PriorityQueue<>(Comparator.comparing((CheckoutCounter c) -> c.time)
                .thenComparing(c -> c.counterNumber));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int customerNum = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            customers[i] = new Customer(customerNum, time);

            if (i < K) {
                pq.add(new CheckoutCounter(time, i, customerNum));
            }
        }

        int outCount = 0;
        int index = K;

        while (!pq.isEmpty()) {
            CheckoutCounter checkoutCounter = pq.poll();
            int time = checkoutCounter.time;
            int counterNumber = checkoutCounter.counterNumber;
            int customerNumber = checkoutCounter.customerNumber;
            outCount++;

            if (outCount == X) {
                System.out.println(customerNumber);
                break;
            }

            if (index < N) {
                pq.add(new CheckoutCounter(time + customers[index].time, counterNumber, customers[index].number));
                index++;
            }
        }
    }

    private static class Customer {
        int number;
        int time;

        public Customer(int number, int time) {
            this.number = number;
            this.time = time;
        }

        public int getNumber() {
            return number;
        }

        public int getTime() {
            return time;
        }
    }

    private static class CheckoutCounter {
        int time;
        int counterNumber;
        int customerNumber;

        public CheckoutCounter(int time, int counterNumber, int customerNumber) {
            this.time = time;
            this.counterNumber = counterNumber;
            this.customerNumber = customerNumber;
        }
    }
}