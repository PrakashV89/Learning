package com.general;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class BadHashFunction {
    public static void main(String[] args) throws InterruptedException {
        Map<EmployeeWithConstantHashCode, Boolean> map = new HashMap<>();
        Map<Employee1, Boolean> map1 = new HashMap<>();

        Instant instant = Instant.now();
        System.out.println(Duration.between(instant, Instant.now()).toSeconds());

        instant = Instant.now();
        final int endExclusive = 90_000;
        IntStream.range(0, endExclusive).forEach(i -> map.put(new EmployeeWithConstantHashCode(i, String.valueOf(i)), true));
        IntStream.range(0, endExclusive).forEach(i -> map.get(new EmployeeWithConstantHashCode(i, String.valueOf(i))));
        System.out.println(Duration.between(instant, Instant.now()).toSeconds());


        instant = Instant.now();
        IntStream.range(0, endExclusive).forEach(i -> map1.put(new Employee1(i, String.valueOf(i)), true));
        IntStream.range(0, endExclusive).forEach(i -> map1.get(new Employee1(i, String.valueOf(i))));
        System.out.println(Duration.between(instant, Instant.now()).toSeconds());


        System.out.println(map.size() == map1.size());
    }

    static class EmployeeWithConstantHashCode {
        int id;
        String employee;

        EmployeeWithConstantHashCode(int id, String Employee){
            this.id = id;
            this.employee = employee;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EmployeeWithConstantHashCode that = (EmployeeWithConstantHashCode) o;
            return id == that.id && Objects.equals(employee, that.employee);
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", employee='" + employee + '\'' +
                    '}';
        }
    }

    static class Employee1{
        int id;
        String employee;

        Employee1(int id, String Employee){
            this.id = id;
            this.employee = employee;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee1 employee1 = (Employee1) o;
            return id == employee1.id && Objects.equals(employee, employee1.employee);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, employee);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", employee='" + employee + '\'' +
                    '}';
        }
    }
}
