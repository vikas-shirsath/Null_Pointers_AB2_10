import React, { useState } from "react";
import axios from "axios";
import FileUpload from "./components/FileUpload";
import ScoreCard from "./components/ScoreCard";
import CircularProgress from "@mui/material/CircularProgress";

const App = () => {
  const [result, setResult] = useState(null); // State to store the result
  const [loading, setLoading] = useState(false); // State to handle loading

  const handleFileUpload = async (file) => {
    const formData = new FormData();
    formData.append("file", file); // Append the file to the form data

    setLoading(true); // Set loading to true

    try {
      console.log("Sending file to backend..."); // Log the request
      const response = await axios.post("http://localhost:8081/api/detect", formData, {
        headers: {
          "Content-Type": "multipart/form-data", // Set the content type
        },
      });

      console.log("Backend response:", response.data); // Log the response

      if (response.data.status === "error") {
        alert(response.data.message); // Display API error message
        return;
      }

      setResult(response.data); // Set the result state
    } catch (error) {
      console.error("Error uploading file:", error);
      if (error.response) {
        // Backend returned an error response
        console.error("Backend error response:", error.response.data); // Log backend error
        alert(`Failed to upload file: ${error.response.data.message}`);
      } else if (error.request) {
        // No response received from the backend
        console.error("No response from backend:", error.request); // Log no response
        alert("Failed to upload file: No response from the server.");
      } else {
        // Other errors
        console.error("Other error:", error.message); // Log other errors
        alert("Failed to upload file: " + error.message);
      }
    } finally {
      setLoading(false); // Set loading to false
    }
  };

  const resetForm = () => {
    setResult(null); // Reset the result state
  };

  return (
    <div className="app-container">
      <h1 style={{ textAlign: "center", color: "#007bff", marginBottom: "20px", marginTop: "40px" }}>
        PII Detector
      </h1>
      <div style={{ paddingTop: "40px" }}>
        <FileUpload onFileUpload={handleFileUpload} />
      </div>
      {loading && (
        <div style={{ textAlign: "center", marginTop: "20px" }}>
          <CircularProgress style={{ color: "#007bff" }} />
          <p style={{ color: "#007bff" }}>Processing file...</p>
        </div>
      )}
      {result && (
        <>
          <ScoreCard result={result} />
          <button
            onClick={resetForm}
            style={{
              display: "block",
              margin: "20px auto",
              padding: "10px 20px",
              backgroundColor: "#007bff",
              color: "#fff",
              border: "none",
              borderRadius: "8px",
              cursor: "pointer",
              fontSize: "16px",
              fontWeight: "500",
              transition: "all 0.3s ease",
            }}
            className="glow-on-hover"
          >
            Upload Another File
          </button>
        </>
      )}
    </div>
  );
};

export default App;