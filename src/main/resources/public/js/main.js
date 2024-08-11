var online = navigator.onLine;
function showMsg(message) {
  document.getElementById("successMsg").innerText = message;
  var btn = document.getElementById("successBtn");
  btn.click();
}
function errorMsg(message) {
  document.getElementById("errorMsg").innerText = message;
  var btn = document.getElementById("errorBtn");
  btn.click();
}

function downloadFile() {
  if (!online) {
    errorMsg("You are Not Connected with Internet 🚨");
    return;
  }
  const api = "https://honest-choice-chigger.ngrok-free.app/api/convert?url=";
  let url = document.getElementById("urlbox").value;
  let fileurl = api + encodeURIComponent(url); // Ensure URL is encoded

  if (url === "") {
    alert("Please Enter the Webpage URL to proceed.");
    return;
  }

  document.getElementsByTagName("button")[1].disabled = true;
  document.getElementsByTagName("button")[2].disabled = true;
  document.getElementById("loader").style.visibility = "visible";
  // Make the fetch request
  fetch(fileurl)
    .then((response) => {
      // Check if the request was successful
      if (!response.ok) {
        document.getElementById("loader").style.visibility = "hidden";
        document.getElementsByTagName("button")[1].disabled = false;
        document.getElementsByTagName("button")[2].disabled = false;
        errorMsg("Something went wrong !🚨");
        throw new Error("Network response was not ok " + response.statusText);
      }

      // Extract filename from Content-Disposition header if present
      let fileName = "response.pdf"; // Default filename
      const contentDisposition = response.headers.get("Content-Disposition");
      if (contentDisposition) {
        const filenameMatch = contentDisposition.match(/filename="([^"]+)"/);
        if (filenameMatch && filenameMatch[1]) {
          fileName = filenameMatch[1];
        }
      }

      // Return the response as a Blob along with the filename
      return response.blob().then((blob) => ({ blob, fileName }));
    })
    .then(({ blob, fileName }) => {
      //show success message
      showMsg("Webpage converted Successfully!");
      // Create a new link element
      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob); // Create a URL for the Blob
      link.download = fileName; // Use the extracted filename
      document.body.appendChild(link); // Append the link to the document
      link.click(); // Programmatically click the link to trigger the download
      document.body.removeChild(link); // Remove the link from the document

      // Optionally revoke the object URL after use
      URL.revokeObjectURL(link.href);

      document.getElementById("loader").style.visibility = "hidden";
      document.getElementsByTagName("button")[1].disabled = false;
      document.getElementsByTagName("button")[2].disabled = false;
    })
    .catch((error) => {
      // Handle errors here
      document.getElementById("loader").style.visibility = "hidden";
      document.getElementsByTagName("button")[1].disabled = false;
      document.getElementsByTagName("button")[2].disabled = false;
      errorMsg("Something went wrong !🚨");
      console.error("There was a problem with the fetch operation:", error);
    });
}
function sendEmail() {
  if (!online) {
    errorMsg("You are Not Connected with Internet 🚨");
    return;
  }
  const api = "https://honest-choice-chigger.ngrok-free.app/api/convert/mail?url=";
  let url = document.getElementById("urlbox").value;
  let email = document.getElementById("emailbox").value;
  let fileurl = api + encodeURIComponent(url) + "&&email=" + email;
  console.log(fileurl);
  if (url === "" || email === "") {
    alert("Please Enter Valid inputs to proceed.");
    return;
  }
  document.getElementsByTagName("button")[3].click();
  document.getElementsByTagName("button")[1].disabled = true;
  document.getElementsByTagName("button")[2].disabled = true;
  document.getElementById("loader").style.visibility = "visible";
  // Make the fetch request
  fetch(fileurl, {
    method: "GET", // You can use 'POST' if your API expects a POST request
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      console.log(response);
      if (response.status != 200) {
        errorMsg("Something went wrong !🚨");
        document.getElementById("loader").style.visibility = "hidden";
        document.getElementsByTagName("button")[1].disabled = false;
        document.getElementsByTagName("button")[2].disabled = false;
        throw new Error("something is wrong ");
      }
    }) // Parse the JSON from the response
    .then((data) => {
      document.getElementById("loader").style.visibility = "hidden";
      document.getElementsByTagName("button")[1].disabled = false;
      document.getElementsByTagName("button")[2].disabled = false;
      showMsg("Webpage converted and sent to : " + email);
      return;
    })
    .catch((error) => {
      document.getElementById("loader").style.visibility = "hidden";
      document.getElementsByTagName("button")[1].disabled = false;
      document.getElementsByTagName("button")[2].disabled = false;
      errorMsg("Please check the Webpage url and Email Address ");
      console.error("Error:", error); // Handle any errors
    });
}
