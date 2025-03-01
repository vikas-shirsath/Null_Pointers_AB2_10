import React from "react";

const ScoreCard = ({ result }) => {
  const calculateScore = () => {
    const totalPii = Object.keys(result).filter((key) => key.endsWith("_matches")).length;
    const detectedPii = Object.keys(result).filter(
      (key) => key.endsWith("_matches") && result[key].length > 0
    ).length;
    return ((detectedPii / totalPii) * 100).toFixed(2);
  };

  const score = calculateScore();
  const hasPii = Object.keys(result).some(
    (key) => key.endsWith("_matches") && result[key].length > 0
  );

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
          <h2 style={{ color: "#007bff", textAlign: "center" }}>PII Detection Score: {score}%</h2>
          <div style={{ marginTop: "10px" }}>
            {Object.keys(result).map(
              (key) =>
                result[key].length > 0 && (
                  <div key={key} style={{ marginBottom: "10px" }}>
                    <strong style={{ color: "#007bff" }}>{key.replace("_matches", "").toUpperCase()}:</strong>
                    <ul style={{ listStyle: "none", paddingLeft: "0" }}>
                      {result[key].map((match, index) => (
                        <li key={index} style={{ marginBottom: "5px", color: "#ffffff" }}>{match}</li>
                      ))}
                    </ul>
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