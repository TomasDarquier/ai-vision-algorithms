# AI Vision Algorithms

[![Java](https://img.shields.io/badge/Java-11%2B-blue.svg)](https://www.oracle.com/java/)
[![Computer Vision](https://img.shields.io/badge/Computer%20Vision-Enabled-success.svg)](https://en.wikipedia.org/wiki/Computer_vision)
[![Neural Networks](https://img.shields.io/badge/Neural%20Networks-Implemented-orange.svg)](https://en.wikipedia.org/wiki/Neural_network)

A comprehensive collection of advanced computer vision and artificial intelligence algorithms implemented in Java. This repository demonstrates practical implementations of image processing, pattern recognition, and search algorithms.

## 🚀 Projects Overview

### 1. Hough Transform Implementation
An implementation of the Hough Transform algorithm for detecting both lines and circles in images.

#### Features:
- Line detection using parametric space transformation
- Circle detection with configurable radius range
- Non-maximal suppression for optimal circle detection
- Edge detection preprocessing
- Customizable threshold parameters

#### Example Results:

##### Line Detection
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <div style="flex: 1; text-align: center;">
        <h6>Before</h6>
        <img src="hough-transform/input_line.png" alt="Line Before" width="400"/>
    </div>
    <div style="flex: 1; text-align: center;">
        <h6>After</h6>
        <img src="hough-transform/output_lines.png" alt="Line After" width="400"/>
    </div>
</div>

##### Circle Detection
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <div style="flex: 1; text-align: center;">
        <h6>Before</h6>
        <img src="hough-transform/input_circle.png" alt="Circle Before" width="400"/>
    </div>
    <div style="flex: 1; text-align: center;">
        <h6>After</h6>
        <img src="hough-transform/output_circles.png" alt="Circle After" width="400"/>
    </div>
</div>

### 2. Hopfield Neural Network
A complete implementation of a Hopfield Neural Network for pattern recognition and memory retrieval.

#### Features:
- Pattern storage and recognition
- Asynchronous update rule
- Energy function minimization
- Convergence to stored patterns
- Noise-tolerant pattern recovery

#### Example Results:
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <div style="flex: 1; text-align: center;">
        <h6>Before</h6>
        <img src="hopfield-network/test_image.png" alt="Image Before" width="400"/>
    </div>
    <div style="flex: 1; text-align: center;">
        <h6>After</h6>
        <img src="hopfield-network/recognized_image.png" alt="Image After" width="400"/>
    </div>
</div>
