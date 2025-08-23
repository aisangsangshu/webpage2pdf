<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
</head>
<body>

<h1>WebPDF</h1>

<p>
  WebPDF is an <strong>online tool</strong> that converts web pages into PDF documents.<br>
  It provides a simple and modern interface built with <strong>HTML, CSS, JavaScript, and Bootstrap</strong>, 
  while the backend is powered by <strong>Spring Boot</strong>.
</p>

<p>
  For PDF generation, it integrates <a href="https://wkhtmltopdf.org"><strong>wkhtml2pdf</strong></a> 
  — special thanks to the team behind it.
</p>

<hr>

<h2>🚀 Features</h2>
<ul>
  <li><strong>Convert Web Pages to PDF</strong> → Enter a URL and instantly generate a PDF.</li>
  <li><strong>Send PDF via Email</strong> → Optionally deliver the generated PDF to any email address.</li>
  <li><strong>User-Friendly UI</strong> → Clean Bootstrap-based interface.</li>
  <li><strong>Backend Powered by Java</strong> → Robust and scalable Spring Boot backend.</li>
</ul>

<hr>

<h2>📑 Table of Contents</h2>
<ol>
  <li><a href="#features">Features</a></li>
  <li><a href="#installation">Installation</a></li>
  <li><a href="#usage">Usage</a></li>
  <li><a href="#configuration">Configuration</a></li>
  <li><a href="#contributing">Contributing</a></li>
  <li><a href="#license">License</a></li>
  <li><a href="#contact">Contact</a></li>
  <li><a href="#acknowledgements">Acknowledgements</a></li>
</ol>

<hr>

<h2 id="installation">⚙️ Installation</h2>

<h3>1. Prerequisites</h3>
<ul>
  <li><a href="https://www.oracle.com/java/technologies/javase-downloads.html">Java JDK 8+</a></li>
  <li><a href="https://wkhtmltopdf.org/downloads.html">wkhtml2pdf</a> (ensure it’s installed and available in your PATH)</li>
</ul>

<h3>2. Clone the Repository</h3>
<pre><code>git clone https://github.com/ikali850/webpage2pdf.git
cd webpage2pdf
</code></pre>

<h3>3. Build &amp; Run</h3>
<pre><code>./mvnw spring-boot:run
</code></pre>

<hr>

<h2 id="usage">📘 Usage</h2>
<ol>
  <li>Open the application in your browser.</li>
  <li>Enter the <strong>web page URL</strong> you want to convert.</li>
  <li>Click <strong>Generate PDF</strong>.</li>
  <li>(Optional) Enter an email to receive the PDF file.</li>
</ol>

<hr>

<h2 id="configuration">🔧 Configuration</h2>
<ul>
  <li><strong>wkhtml2pdf Path</strong>: Ensure the executable path is correctly set in your system.</li>
  <li><strong>Email Settings</strong>: Configure SMTP in <code>application.properties</code>:</li>
</ul>

<pre><code>spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
</code></pre>

<hr>

<h2 id="contributing">🤝 Contributing</h2>
<p>Contributions are welcome!</p>
<ol>
  <li>Fork the repository</li>
  <li>Create a feature branch (<code>git checkout -b feature-name</code>)</li>
  <li>Commit your changes (<code>git commit -m "Added feature"</code>)</li>
  <li>Push to your branch (<code>git push origin feature-name</code>)</li>
  <li>Open a Pull Request</li>
</ol>

<hr>

<h2 id="license">📜 License</h2>
<p>This project is licensed under the <strong>MIT License</strong>.</p>

<hr>

<h2 id="contact">📬 Contact</h2>
<ul>
  <li><strong>Author</strong>: Arvind Kumar</li>
  <li><strong>GitHub</strong>: <a href="https://github.com/ikali850">ikali850</a></li>
  <li><strong>Instagram</strong>: <a href="https://instagram.com/themannu850">@themannu850</a></li>
</ul>

<hr>

<h2 id="acknowledgements">🙏 Acknowledgements</h2>
<ul>
  <li><a href="https://wkhtmltopdf.org">wkhtml2pdf</a> for PDF conversion.</li>
  <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a> for backend support.</li>
  <li><a href="https://getbootstrap.com">Bootstrap</a> for frontend design.</li>
</ul>

</body>
</html>
