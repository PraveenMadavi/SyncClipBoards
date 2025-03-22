package com.linkerbk.linker.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "TempUsers")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int connectionKey;

    @ManyToOne
    @JoinColumn(name = "clipboard_id") // Foreign key
    private Clipboard clipboard;


}
