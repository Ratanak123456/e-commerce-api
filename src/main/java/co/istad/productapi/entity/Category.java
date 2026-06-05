package co.istad.productapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Category {
    private Integer id;
    private String name;
    private String slug;
    private String des;
    private String icon;
    private Boolean isDeleted;
}
