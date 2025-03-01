import React from "react";
import PieChart from "./PieChart";

const ScoreCard = ({ result }) => {
  const calculateScore = () => {
    const totalPii = Object.keys(result).filter((key) => key.endsWith("_matches")).length;
    const detectedPii = Object.keys(result).filter(
      (key) => key.endsWith("_matches") && result[key].length > 0
    ).length;
    return ((detectedPii / totalPii) * 100).toFixed(2); // Calculate the percentage
  };

  const score = calculateScore();
  const hasPii = Object.keys(result).some(
    (key) => key.endsWith("_matches") && result[key].length > 0
  );

  // Format the results for better readability
  const formatResult = (key, value) => {
    switch (key) {
      case "dob_matches":
        return `DOB: ${value.join(", ")}`;
      case "aadhaar_matches":
        return `AADHAAR: ${value.join(", ")}`;
      case "name_matches":
        return `NAME: ${value.join(", ")}`;
      default:
        return `${key.replace("_matches", "").toUpperCase()}: ${value.join(", ")}`;
    }
  };

  return (
    <div
      style={{
        marginTop: "20px",
        padding: "20px",
        border: "1px solid #333",
        borderRadius: "12px",
        backgroundColor: "#1e1e1e",
        color: "#ffffff",
      }}
    >
      {hasPii ? (
        <>
          <h2 style={{ color: "#007bff", textAlign: "center" }}>PII Detection Score</h2>
          <div style={{ width: "300px", margin: "0 auto" }}>
            <PieChart score={parseFloat(score)} />
          </div>
          <p style={{ textAlign: "center", color: "#007bff", marginTop: "10px" }}>
            PII Detected: {score}%
          </p>
          <div style={{ marginTop: "20px" }}>
            {Object.keys(result).map(
              (key) =>
                result[key].length > 0 && (
                  <div key={key} style={{ marginBottom: "10px" }}>
                    <strong style={{ color: "#007bff" }}>
                      {formatResult(key, result[key])}
                    </strong>
                  </div>
                )
            )}
          </div>
        </>
      ) : (
        <p style={{ textAlign: "center", color: "#007bff" }}>No PII detected in the uploaded file.</p>
      )}
    </div>
  );
};

export default ScoreCard;