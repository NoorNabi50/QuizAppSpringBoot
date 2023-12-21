  <h1>QuizAppSpringBoot</h1>
    <p>QuizAppSpringBoot is a Java Spring Boot application that allows users to create, manage, and take quizzes. It uses Spring Boot, Java Persistence API (JPA), and PostgreSQL database.</p>
    <h2>Endpoints</h2>
    <ul>
        <li><strong>Get All Questions:</strong> <a href="http://localhost:8080/question/getAllQuestions">http://localhost:8080/question/getAllQuestions</a></li>
        <li><strong>Get All Questions by Category:</strong> <a href="http://localhost:8080/question/getQuestionByCategory/art">http://localhost:8080/question/getQuestionByCategory/art</a></li>
        <li><strong>Save Question:</strong> <a href="http://localhost:8080/question/save">http://localhost:8080/question/save</a></li>
        <li><strong>Create Quiz:</strong> <a href="http://localhost:8080/quiz/create?category=Art&title=Artist%20Quiz&totalQs=5">http://localhost:8080/quiz/create?category=Art&title=Artist%20Quiz&totalQs=5</a></li>
        <li><strong>Get Quiz Questions:</strong> <a href="http://localhost:8080/quiz/getQuizQuestions/2">http://localhost:8080/quiz/getQuizQuestions/2</a></li>
        <li><strong>Submit Quiz:</strong> <a href="http://localhost:8080/quiz/submitQuiz/{1}">http://localhost:8080/quiz/submitQuiz/{1}</a></li>
    </ul>
    <h2>Getting Started</h2>
    <p>Follow these steps to set up and run the QuizAppSpringBoot application:</p>
    <ol>
        <li>Clone the repository to your local machine.</li>
        <li>Configure your PostgreSQL database settings in the application.properties file.</li>
        <li>Build the project using Maven: <code>mvn clean install</code></li>
        <li>Run the application: <code>mvn spring-boot:run</code></li>
        <li>Access the API endpoints as described above.</li>
    </ol>
    <h2>Contributing</h2>
  
  <h2>License</h2>
    <p>This project is licensed under the MIT License. See the LICENSE file for details.</p>
   
