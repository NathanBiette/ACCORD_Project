# ACCORD Project

Code archive of the ACCORD project. 

## Description

ACCORD was a prototype of multi camera automatic recording system of concert.  
It was developed over a year by students at Telecom Paris from Nov 2016 to Jun 2017.  
It was a proof of concept made of multiple software and hardware components, mostly written in java.  

Team : Nathan Biette, Félix Gaschi, Pierre Grandieres, Dimitri Hamzaoui, Thibaud Lemaire, Iann Voltz, Hend Zouari

| ![PACT 5 4  Poster Accord](https://user-images.githubusercontent.com/28588368/154859120-d070b032-0b7e-4599-b175-19665afe8314.jpg) | 
|:--:| 
| *Project Poster (in French)* |

## My Contributions 

### Video streaming

I used FFMPEG to open the video feed of multiple cameras and used custom FFMPEG filter to split and crop and switch between the feeds on the fly.

| <img src="https://user-images.githubusercontent.com/28588368/154859814-49a201d0-e6ff-4d32-b420-d9bcd79c797a.jpg" width="500"> | 
|:--:| 
| *The filter graph used for FFMPEG* |

The editing is done on the fly as FFMPEG switches from one stream to the other to write to into the final video file. The crop window is also moved on the fly.  
Here we can see some early tests.  

https://user-images.githubusercontent.com/28588368/154861308-1c3a009e-6b61-43d1-854c-d7108bf1eacd.mp4

The videos are streamed toward a mobile device over UDP for live preview purposes.  
Another member of the team developed the app to play the live video broadcast and select which source to choose from for the editing.  

### Motion tracking

We used FFMPEG to extract a cropped video stream out of a bigger video stream (like a 4K). This cropped video is then moved around using a motion tracking algorithm based on OpenCV fed with the video stream.  

I helped in the implementation of the motion tracking component based on SURF descriptors.  

| <img src="https://user-images.githubusercontent.com/28588368/154860278-dc5ec658-c753-4dca-aa4d-91f0160e7aaa.png" width="500"> | 
|:--:| 
| *SURF descriptors exemple* |

A new frame from the video is analysed and descriptors are exctracted and compared to the previous frame in order to find the movement.  
Outliers are rejected and the average movement is estimated and filtered then sent to the cropping module to move the crop window of the same amount.  

Here we can see our result of motion tracking driving a window cropped out of a higher resolution video stream.  

https://user-images.githubusercontent.com/28588368/154860148-2c7cc7f3-8484-47c7-9ad9-2954181b8147.mp4

We use a custom FFMPEG which was modified to take coordinates determined by the motion tracking into account for the cropping.  

## Other components of the project

### The Android app

To receive the video stream and select which stream to include in the live editing, we developed an app on a mobile device.  
The goal was to preview which camera wasa best for editing and select it or let the system select it automatically and also determine which instruments were on stage.  

| <img src="https://user-images.githubusercontent.com/28588368/154860069-1dde3443-cd41-47d4-a87c-6f72c15d11d5.png" width="500"> | 
|:--:| 
| *The Android App* |

### Mix estimation 

There was an audio analysis component to the project. The programm was supposed to detect which instrument was playing more prominently and oriente the camera towards it.  

### Camera orientation and zoom

One of the camera was supposed to be on a rotating base and be driven by the app and mix estimation to automatically shoot the currently "most interesting" musician on stage.
