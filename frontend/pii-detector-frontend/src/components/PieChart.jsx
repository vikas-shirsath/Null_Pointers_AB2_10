import React from "react";
import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

const PieChart = ({ score }) => {
  const data = {
    labels: ["PII Detected", "PII Not Detected"],
    datasets: [
      {
        data: [score, 100 - score],
        backgroundColor: [
          `rgba(0, 123, 255, ${score / 100})`, // Dynamic opacity based on score
          "rgba(255, 255, 255, 0.1)", // Light gray for the remaining portion
        ],
        borderColor: ["rgba(0, 123, 255, 1)", "rgba(255, 255, 255, 0.5)"],
        borderWidth: 1,
      },
    ],
  };

  const options = {
    plugins: {
      legend: {
        display: true,
        position: "bottom",
        labels: {
          color: "#ffffff", // White text for legend
        },
      },
    },
  };

  return <Pie data={data} options={options} />;
};

export default PieChart;