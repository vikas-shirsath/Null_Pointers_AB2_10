import React, { useState } from "react";
import axios from "axios";
import FileUpload from "./components/FileUpload";
import ScoreCard from "./components/ScoreCard";
import CircularProgress from "@mui/material/CircularProgress";

const App = () => {
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleFileUpload = async (file) => {
    const formData = new FormData();
    formData.append("file", file);

    setLoading(true);

    try {
      const response = await axios.post("http://localhost:8081/api/detect", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      if (response.data.status === "error") {
        alert(response.data.message);
        return;
      }

      setResult(response.data);
    } catch (error) {
      console.error("Error uploading file:", error);
      alert("Failed to upload file. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const resetForm = () => {
    setResult(null);
  };

  return (
    <div className="app-container">
      <h1 style={{ textAlign: "center", color: "#007bff", marginBottom: "20px", marginTop: "40px" }}>PII Detector</h1>
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