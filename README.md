# Workshop_MongoDB

<p> The biggest challenge of this project was to transform the id of the document that was in timestamp format to a Long. I couldn't get support in the mongo db database for this automatic conversion, so I created the MongoComponent and ConfigurationMongo classes to accomplish this task. </p>

<p> The project is a small fraction of how a social network works, with users, posts and comments. </p>

<h1> Class Diagram </h1>

```mermaid

classDiagram
  class User {
    - long idAsLong
    - String name
    - String email
    - String password
  }

 class Post {
    - Date date
    - long idAsLong
    - String title
    - String body
  }

  class UserDTO {
    - long idAsLong
    - String name
    - String email
  }

class ComentDTO {
    - String text
    - Date date
  }

    User "1" -- "..N" Post
    UserDTO "1" -- "1" ComentDTO
    Post "1" -- "..N" ComentDTO
```

