//package kz.erasyl.volunteerback.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "image_table")
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class Image {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long imageID;
//
//    private String imageName;
//
//    private String type;
//
//    @Lob
//    @Column(name = "picByte", length = 5000)
//    private byte[] picByte;
//
//    @OneToOne
//    @JoinColumn(name = "users_id", referencedColumnName = "user_id")
//    private User users_id;
//}
