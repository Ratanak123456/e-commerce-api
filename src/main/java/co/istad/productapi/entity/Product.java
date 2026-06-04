package co.istad.productapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product {
    private int id;
    private String name;
    private String dec;
    private Float price;
    private int userId;
}
