import React, { useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";

const FileUpload = ({ onFileUpload }) => {
  const [error, setError] = useState(null);
  const [fileName, setFileName] = useState(null);

  const onDrop = useCallback(
    (acceptedFiles) => {
      const file = acceptedFiles[0];
      const allowedTypes = ["application/pdf", "text/plain", "image/jpeg", "image/png"];
      const maxSize = 10 * 1024 * 1024; // 10MB

      if (!allowedTypes.includes(file.type)) {
        setError("Unsupported file type. Please upload a PDF, text, JPEG, or PNG file.");
        return;
      }

      if (file.size > maxSize) {
        setError("File size exceeds the limit of 10MB.");
        return;
      }

      setError(null); // Clear any previous errors
      setFileName(file.name); // Set the file name
      onFileUpload(file);
    },
    [onFileUpload]
  );

  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  return (
    <div style={{ paddingTop: "40px" }}>
      <div
        {...getRootProps()}
        style={{
          border: "2px dashed #007bff",
          borderRadius: "12px",
          padding: "20px",
          textAlign: "center",
          cursor: "pointer",
          color: "#007bff",
          margin: "20px 0",
          background: "#1e1e1e", // Dark background
          transition: "all 0.3s ease",
        }}
        className="glow-on-hover"
      >
        <input {...getInputProps()} />
        <p style={{ transition: "color 0.3s ease" }}>Drag & drop a file here, or click to select a file</p>
        <p style={{ fontSize: "12px", color: "#666", transition: "color 0.3s ease" }}>Max file size: 10MB</p>
      </div>
      {fileName && <p style={{ textAlign: "center", color: "#007bff" }}>Selected file: {fileName}</p>}
      {error && <p style={{ color: "#ff4444", textAlign: "center" }}>{error}</p>}
    </div>
  );
};

export default FileUpload;