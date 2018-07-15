var registers = [];
var programCounter = 0;
var accumulator = 0;
var instructionRegister = 0;
var addressRegister = 0;

var input;
var output;

var instructions = [];
var instructionList = ["ADD", "SUB", "STA", "SKIP", "LDA", "BRA", "BRZ", "BRP"];


var instructionInput;
var runProgramButton;

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}
function Register(x, y, index) {
	this.value = "000";
	this.x = x;
	this.y = y;
	this.index = index;
	this.col = 255;
	this.draw = function () {
		fill (this.col);
		rect(this.x, this.y, 50, 50);
		fill(0);
		textSize(20);
		text(this.value, this.x + 25, this.y + 30);
		fill (255);
		textSize(14);
		fill(0);
		
		text(this.index, this.x + 25, this.y);
		fill (255);
		
	}
}

function pad(number, length) {
   
    var str = '' + number;
    while (str.length < length) {
        str = '0' + str;
    }
   
    return str;

}
function setup() {
	stroke(0);
	var canvas = createCanvas(1600, 800);

	var x = (windowWidth - width) / 2;
	var y = (windowHeight - height) / 2;
	canvas.position(x, y + 50);
	textAlign(CENTER);

	for (var i = 0; i < 10; i++) {
		for (var j = 0; j < 10; j++) {

			var r = new Register((j + 1) * 70, (i + 1) * 75 - 30, (i * 10) + j);

			registers.push(r);
		}
	}
	instructionInput = createInput("");
	instructionInput.position(900, 150);
	instructionInput.size(600, 100);
	runProgramButton = createButton("Run Program");
	runProgramButton.position(instructionInput.x + instructionInput.width, instructionInput.y);
	runProgramButton.size(100, 100);

	runProgramButton.mousePressed(() => {
		var a = instructionInput.value().split(",");
		console.log(a)
		cycle(a);

	});

}

function draw() {
	background(255);
	rect(0, 0, width - 10, height - 10);
	for (var i = 0; i < registers.length; i++) {
		registers[i].draw();
	}

	rect(800, 200, 150, 80);
	rect(800, 200, 150, 30);

	rect(800, 300, 150, 80);
	rect(800, 300, 150, 30);

	rect(800, 400, 150, 80);
	rect(800, 400, 150, 30);

	rect(800, 500, 150, 80);
	rect(800, 500, 150, 30);
	fill (0);
	text("Program Counter", 880, 220);
	text("Accumulator", 880, 320);
	text("Instruction Register", 880, 420);
	text("Address Register", 880, 520);
	
	text(pad(programCounter,3), 880, 250);
	text(pad(accumulator,3), 880, 350);
	text(pad(instructionRegister, 3), 880, 450);
	text(pad(addressRegister,3), 880, 550);
	fill (255);
}



async function cycle(instructions) {
	console.log("running");
	fetch(instructions);
	while (true) {
		registers[programCounter].col = color(255,0,0)
		await sleep(1000);
		var instruction = registers[programCounter].value;
		registers[programCounter].col = 255;
		if (instruction == 0) {
			break;
		}
		programCounter++;
		instructionRegister = Math.floor(instruction / 100);
		addressRegister = instruction - (instructionRegister * 100);
		
		execute();
	}
}

function fetch(instructions) {

	for (let i = 0; i < instructions.length; i++) {
		var x = parse(instructions[i]);

		registers[i].value = x;
	}


}

function execute() {
	
	switch (instructionRegister) {
		case 1:
			accumulator += registers[addressRegister].value;
			console.log("accumulator : " + accumulator);
			break;
		case 2:
			accumulator -= registers[addressRegister].value;
			break;
		case 3:
			registers[addressRegister].value = accumulator;
			break;
		case 4:
			console.log("Unknown command");
			break;
		case 5:
			accumulator = registers[addressRegister].data;
			break;
		case 6:
			programCounter = addressRegister;
			break;
		case 7:
			if (accumulator == 0) {
				programCounter = addressRegister;
			}
			break;
		case 8:
			if (accumulator >= 0) {
				programCounter = addressRegister;
			}
			break;
		case 9:
			if (addressRegister == 1) {
				accumulator = 2;
				console.log("input");

			} else if (addressRegister == 2) {
				console.log(accumulator);
			}
			break;
	}
}

function parse(str) {
	var instruct;

	if (str == "INP") {
		instruct = 901;
	} else if (str == "OUT") {
		instruct = 902;
	} else if (str == "HLT") {
		instruct = 0;
	} else {
		for (var i = 0; i < instructionList.length; i++) {

			if (i == 3) {
				i++;
			}
			var reg = RegExp("(" + instructionList[i] + ")" + " (\\d\\d)", 'g');
			var m = reg.exec(str);


			if (m != null) {
				instruct = (i + 1).toString() + m[2];

			}

		}
	}

	return instruct;
}