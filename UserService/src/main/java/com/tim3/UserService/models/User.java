package com.tim3.UserService;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "info", nullable = true)
    private String info;

    @OneToMany( mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<UserMatch> userMatchList;
}