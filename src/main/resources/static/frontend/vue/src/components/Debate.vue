<template>
    <div>
        <div class="debate-list">
            <div class="btn">
                <button v-on:click="add()">추가</button>
                <button v-on:click="cancel()">취소</button>
            </div>
            <div class="edit" v-if="EditMode === 1">
                <form method="POST" action="http://127.0.0.1:8081/addDebate">
                    <input name="title" class="title" type="text" v-model="title" />
                    <textarea name="content" class="content" v-model="content"></textarea>
                    <button class="btn" type="submit">저장</button>
                </form>
            </div>
            <div class="edit" v-if="EditMode === 2">
                <form method="POST" action="http://127.0.0.1:8081/addDebate">
                    <input name="title" class="title" type="text" v-model="title" />
                    <div class="streaming">
                        <video id="localVideo" autoplay playsinline controls="false" />
                        <select id="availableCameras"></select>
                    </div>
                    <div class="streaming">
                        <video id="remoteVideo" autoplay playsinline controls="false" />
                        <select id="availableCameras"></select>
                    </div>
                </form>
            </div>
            <div class="edit" v-if="! EditMode">
                <div
                    class="debate-content-list"
                    v-for="i in debateList"
                    v-bind:key="i.no"
                    v-on:click="clickContent(i.no)"
                >{{ i.title }}</div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios";
// const constraints = {
//   'video': true,
//   'audio': true
// }

// navigator.mediaDevices.getUserMedia(constraints)
//   .then(stream => {
//     console.log('Got MediaStream:', stream)
//   })
//   .catch(error => {
//     console.error('Error accessing media devices.', error)
//   })

// function getConnectedDevices (type, callback) {
//   navigator.mediaDevices.enumerateDevices()
//     .then(devices => {
//       const filtered = devices.filter(device => device.kind === type)
//       callback(filtered)
//     })
// }

// getConnectedDevices('videoinput', cameras => console.log('Cameras found', cameras))

// Updates the select element with the provided set of cameras
// function updateCameraList (cameras) {
//   const listElement = document.querySelector('select#availableCameras')
//   listElement.innerHTML = ''
//   cameras.map(camera => {
//     const cameraOption = document.createElement('option')
//     cameraOption.label = camera.label
//     cameraOption.value = camera.deviceId
//   }).forEach(cameraOption => listElement.add(cameraOption))
// }

// Fetch an array of devices of a certain type

// async function getConnectedDevices (type) {
//   // const devices = await navigator.mediaDevices.enumerateDevices()
//   // return devices.filter(device => device.kind === type)
// }

// // Get the initial set of cameras connected
// const videoCameras = getConnectedDevices('videoinput')
// updateCameraList(videoCameras)

// // Listen for changes to media devices and update the list accordingly
// if (navigator.mediaDevices !== undefined) {
//   navigator.mediaDevices.addEventListener('devicechange', event => {
//     const newCameraList = getConnectedDevices('video')
//     updateCameraList(newCameraList)
//   })
// }
// Open camera with at least minWidth and minHeight capabilities
// async function openCamera (cameraId, minWidth, minHeight) {
//   const constraints = {
//     'audio': {'echoCancellation': true},
//     'video': {
//       'deviceId': cameraId,
//       'width': {'min': minWidth},
//       'height': {'min': minHeight}
//     }
//   }
//   return navigator.mediaDevices.getUserMedia(constraints)
// }

// const cameras = getConnectedDevices('videoinput')
// console.log(cameras)

// async function playVideoFromCamera () {
//   try {
//     const constraints = {'video': true, 'audio': true}
//     const stream = await navigator.mediaDevices.getUserMedia(constraints)
//     const videoElement = document.querySelector('video#localVideo')
//     videoElement.srcObject = stream
//   } catch (error) {
//     console.error('Error opening video camera.', error)
//   }
// }
// playVideoFromCamera()
// Set up an asynchronous communication channel that will be
// used during the peer connection setup
// const signalingChannel = new WebSocket('ws://172.30.1.11:8081')
// signalingChannel.addEventListener('message', message => {
//   // New message from remote client received
// })

// Send an asynchronous message to the remote client
// signalingChannel.send('Hello!')

// async function makeCall() {
//   const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]}
//   const peerConnection = new RTCPeerConnection(configuration)
//   signalingChannel.addEventListener('message', async message => {
//     if (message.answer) {
//       const remoteDesc = new RTCSessionDescription(message.answer)
//       await peerConnection.setRemoteDescription(remoteDesc)
//     }
//   })
//     const offer = await peerConnection.createOffer()
//     await peerConnection.setLocalDescription(offer)
//     signalingChannel.send({'offer': offer})
// }
// const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]}
// const peerConnection = new RTCPeerConnection(configuration)
// signalingChannel.addEventListener('message', async message => {
//   if (message.offer) {
//     peerConnection.setRemoteDescription(new RTCSessionDescription(message.offer))
//     const answer = await peerConnection.createAnswer()
//     await peerConnection.setLocalDescription(answer)
//     signalingChannel.send({'answer': answer})
//   }
// })

// Listen for local ICE candidates on the local RTCPeerConnection
// peerConnection.addEventListener('icecandidate', event => {
//   if (event.candidate) {
//     signalingChannel.send({'new-ice-candidate': event.candidate})
//   }
// })

// Listen for remote ICE candidates and add them to the local RTCPeerConnection
// signalingChannel.addEventListener('message', async message => {
//   if (message.iceCandidate) {
//     try {
//       await peerConnection.addIceCandidate(message.iceCandidate)
//     } catch (e) {
//       console.error('Error adding received ice candidate', e)
//     }
//   }
// })

// Listen for connectionstatechange on the local RTCPeerConnection
// peerConnection.addEventListener('connectionstatechange', event => {
//   if (peerConnection.connectionState === 'connected') {
//     // Peers connected!
//   }
// })

// if (cameras && cameras.length > 0) {
//   // Open first available video camera with a resolution of 1280x720 pixels
//   const stream = openCamera(cameras[0].deviceId, 1280, 720)
//   stream.getTracks().forEach(track => {
//     console.log(stream)
//     peerConnection.addTrack(track, stream)
//   })
// }

// const remoteStream = new MediaStream()
// const remoteVideo = document.querySelector('#remoteVideo')
// remoteVideo.srcObject = remoteStream

// peerConnection.addEventListener('track', async (event) => {
//   remoteStream.addTrack(event.track, remoteStream)
// })

export default {
    name: "debate",
    data() {
        return {
            title: undefined,
            content: null,
            EditMode: 0,
            debateList: []
        };
    },
    methods: {
        add() {
            this.EditMode = 1;
        },
        cancel() {
            this.EditMode = 0;
            console.log(this.debateList);
        },
        save() {
            this.EditMode = 0;
        },
        clickContent(boardNo) {
            let params = new URLSearchParams();
            params.append("bNo", boardNo);
            axios
                .post("http://localhost:8081/getDebateContent/", params)
                .then(response => {
                    console.log(response);
                    this.debateList[0] = response.data;
                    console.log(this.debateList);
                    this.EditMode = 2;
                    this.title = response.data.title;
                    this.content = response.data.content;
                })
                .catch(e => {
                    console.log("error : ", e.response);
                });

            const constraints = {
                video: true,
                audio: true
            };
            navigator.mediaDevices
                .getUserMedia(constraints)
                .then(stream => {
                    console.log("Got MediaStream:", stream);
                })
                .catch(error => {
                    console.error("Error accessing media devices.", error);
                });

            // function getConnectedDevices (type, callback) {
            //   navigator.mediaDevices.enumerateDevices()
            //     .then(devices => {
            //       const filtered = devices.filter(device => device.kind === type)
            //       callback(filtered)
            //     })
            // }

            getConnectedDevices("videoinput", cameras =>
                console.log("Cameras found", cameras)
            );

            // Updates the select element with the provided set of cameras
            function updateCameraList(cameras) {
                // const listElement = document.querySelector('select#availableCameras')
                // listElement.innerHTML = ''
                // cameras.map(camera => {
                //   const cameraOption = document.createElement('option')
                //   cameraOption.label = camera.label
                //   cameraOption.value = camera.deviceId
                // }).forEach(cameraOption => listElement.add(cameraOption))
            }

            // Fetch an array of devices of a certain type
            async function getConnectedDevices(type) {
                const devices = await navigator.mediaDevices.enumerateDevices();
                return devices.filter(device => device.kind === type);
            }

            // Get the initial set of cameras connected
            const videoCameras = getConnectedDevices("videoinput");
            updateCameraList(videoCameras);

            // Listen for changes to media devices and update the list accordingly
            navigator.mediaDevices.addEventListener("devicechange", event => {
                const newCameraList = getConnectedDevices("video");
                updateCameraList(newCameraList);
            });

            // Open camera with at least minWidth and minHeight capabilities
            async function openCamera(cameraId, minWidth, minHeight) {
                const constraints = {
                    audio: { echoCancellation: true },
                    video: {
                        deviceId: cameraId,
                        width: { min: minWidth },
                        height: { min: minHeight }
                    }
                };
                return navigator.mediaDevices.getUserMedia(constraints);
            }

            const cameras = getConnectedDevices("videoinput");
            console.log(cameras);
            if (cameras && cameras.length > 0) {
                // Open first available video camera with a resolution of 1280x720 pixels
                const stream = openCamera(cameras[0].deviceId, 1280, 720);
                console.log(stream);
            }

            async function playVideoFromCamera() {
                try {
                    const constraints = { video: true, audio: true };
                    const stream = await navigator.mediaDevices.getUserMedia(
                        constraints
                    );
                    const videoElement = document.querySelector(
                        "video#localVideo"
                    );
                    videoElement.srcObject = stream;
                } catch (error) {
                    console.error("Error opening video camera.", error);
                }
            }
            playVideoFromCamera();
            console.log(MediaStreamTrack.getSettings);

            // async function playVideoFromCamera2 () {
            //   try {
            //     const constraints = {'video': true, 'audio': true}
            //     const stream = await navigator.mediaDevices.getUserMedia(constraints)
            //     const videoElement = document.querySelector('video#remoteVideo')
            //     videoElement.srcObject = stream
            //   } catch (error) {
            //     console.error('Error opening video camera.', error)
            //   }
            // }
            playVideoFromCamera();
            // playVideoFromCamera2()

            // const signalingChannel = new WebSocket('ws://172.30.1.8:8081')
            // signalingChannel.addEventListener('message', message => {
            //   // New message from remote client received
            // })

            // Send an asynchronous message to the remote client
            // signalingChannel.send('Hello!')

            // async function makeCall () {
            //   const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]}
            //   const peerConnection = new RTCPeerConnection(configuration)
            //   signalingChannel.addEventListener('message', async message => {
            //     if (message.answer) {
            //       const remoteDesc = new RTCSessionDescription(message.answer)
            //       await peerConnection.setRemoteDescription(remoteDesc)
            //     }
            //   })
            //   const offer = await peerConnection.createOffer()
            //   await peerConnection.setLocalDescription(offer)
            //   signalingChannel.send({'offer': offer})
            // }
            // makeCall()
            // const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]}
            // const peerConnection = new RTCPeerConnection(configuration)
            // signalingChannel.addEventListener('message', async message => {
            //   if (message.offer) {
            //     peerConnection.setRemoteDescription(new RTCSessionDescription(message.offer))
            //     const answer = await peerConnection.createAnswer()
            //     await peerConnection.setLocalDescription(answer)
            //     signalingChannel.send({'answer': answer})
            //   }
            // })

            // // Listen for local ICE candidates on the local RTCPeerConnection
            // peerConnection.addEventListener('icecandidate', event => {
            //   if (event.candidate) {
            //     signalingChannel.send({'new-ice-candidate': event.candidate})
            //   }
            // })

            // // Listen for remote ICE candidates and add them to the local RTCPeerConnection
            // signalingChannel.addEventListener('message', async message => {
            //   if (message.iceCandidate) {
            //     try {
            //       await peerConnection.addIceCandidate(message.iceCandidate)
            //     } catch (e) {
            //       console.error('Error adding received ice candidate', e)
            //     }
            //   }
            // })

            // // Listen for connectionstatechange on the local RTCPeerConnection
            // peerConnection.addEventListener('connectionstatechange', event => {
            //   if (peerConnection.connectionState === 'connected') {
            //     // Peers connected!
            //   }
            // })

            // if (cameras && cameras.length > 0) {
            //   // Open first available video camera with a resolution of 1280x720 pixels
            //   const stream = openCamera(cameras[0].deviceId, 1280, 720)
            //   stream.getTracks().forEach(track => {
            //     console.log(stream)
            //     peerConnection.addTrack(track, stream)
            //   })
            // }

            // const remoteStream = navigator.mediaDevices.getUserMedia(constraints)
            // const remoteVideo = document.querySelector('video#remoteVideo')
            // remoteVideo.srcObject = remoteStream

            // peerConnection.addEventListener('track', async (event) => {
            //   remoteStream.addTrack(event.track, remoteStream)
            // })
        },
        getDebate() {
            axios
                .get("http://localhost:8081/getDebate/")
                .then(response => {
                    console.log(response);
                    this.debateList = response.data;
                    console.log(this.debateList);
                })
                .catch(e => {
                    console.log("error : ", e);
                });
        }
    },
    created: function() {
        axios
            .get("http://localhost:8081/getDebate/")
            .then(response => {
                console.log(response);
                this.debateList = response.data;
                console.log(this.debateList);
            })
            .catch(e => {
                console.log("error : ", e);
            });
    }
};
</script>
<style>
.debate-list {
    width: 80%;
    margin: 0 0 0 10%;
    height: 100%;
}
.btn {
    float: right;
}
.edit {
    clear: both;
}
input {
    width: 100%;
    height: 20px;
    margin: 10px 0 10px 0;
}
textarea {
    width: 100%;
    height: 300px;
}
.debate-content-list {
    width: 100%;
    height: 10%;
    border: 1px solid antiquewhite;
}
.streaming {
    width: 100%;
    height: 500px;
    border: 1px solid;
}
</style>
