const os = require('os');

const interfaces = os.networkInterfaces();
let address;

// Iterate over each network interface
Object.keys(interfaces).forEach((name) => {
  interfaces[name].forEach((interface) => {
    // Check if the interface is IPv4 and not internal
    if (interface.family === 'IPv4' && !interface.internal) {
      address = interface.address;
    }
  });
});

module.exports = address