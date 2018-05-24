// CLSFF (Command Line Shell For Foolish)
// Copyright 2018 Daiki Yoshida
//
// This file is part of CLSFF.
//
// CLSFF is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// CLSFF is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with CLSFF. If not, see <http://www.gnu.org/licenses/>.

import java.io.*;
import clsff.CommandProcessor;
import clsff.CurrentWorkingDirectory;

class Main {
	public static void main(String[] args) {
		try {
			switch(args.length) {
			case 0:
				CommandLine();
				break;

			case 1:
				CommandProcessor.script(new File(args[1]));
				break;

			default:
				System.out.println("Usage: java -jar shell.jar <script-filename>");
				System.exit(1);
			}
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void CommandLine() {
		System.out.println("CLSFF (Command Line Shell For Foolish) Copyright 2018 Daiki Yoshida");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY.");
		System.out.println("This is free software, and you are welcome to redistribute it");
		System.out.println("under certain conditions.");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		CurrentWorkingDirectory cwd = new CurrentWorkingDirectory();

		while(true) {
			System.out.print('>');

			String command;
			try {
				command = reader.readLine();
				if(command == null || command.equals("")) {
					continue;
				}
			} catch(IOException e) {
				throw new clsff.SystemException("I/O error");
			}

			CommandProcessor.CommandProcess(CommandProcessor.SplitCommand(command));
			System.out.println();
		}
	}
}
