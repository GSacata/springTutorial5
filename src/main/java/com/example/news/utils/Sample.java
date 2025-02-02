// package com.example.news.utils;

// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.List;

// import com.example.news.domain.Comment;
// import com.example.news.domain.News;

// public class Sample {
//     public static List<News> sampleNews() {
//         List<News> newsList = new ArrayList<News>();
//         Instant now = Utils.returnFormattedNow();

//         newsList.add(News.builder()
//             .headline("Título 1")
//             .author("Fulano de tal")
//             .content("Praesentium vel et similique unde adipisci voluptatem architecto. Aliquam iusto odio temporibus maiores. Corrupti est et ut et assumenda occaecati.")
//             .publicationMoment(now)
//             .build()
//         );

//         newsList.add(News.builder()
//             .headline("Título 2")
//             .author("Ciclano de tal")
//             .content("""
//                     Nihil qui consectetur qui doloremque est aut. Natus tempora rerum fuga esse facere labore qui. Quam est unde quisquam officiis corrupti officia. Et deserunt deserunt qui molestiae delectus ipsum dicta reiciendis. Molestiae molestiae saepe sunt. Suscipit natus quia quia.
//                     """)
//             .publicationMoment(now)
//             .build()
//         );

//         return newsList;
//     }

//     public static List<Comment> sampleComments() {
//         List<Comment> commentList = new ArrayList<Comment>();
//         Instant now = Utils.returnFormattedNow();

//         commentList.add(Comment.builder()
//             .author("Um comentarista")
//             .content("Muito bom")
//             .publicationMoment(now)
//             .build()
//         );

//         commentList.add(Comment.builder()
//             .author("Outro comentarista")
//             .content("Não é bom")
//             .publicationMoment(now)
//             .build()
//         );

//         return commentList;
//     }
// }
