package me.Mastervrunner.TntTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

///import net.minecraft.server.v1_15_R1.Material;

/*import me.Mastervrunner.Hello_World.Command;
import me.Mastervrunner.Hello_World.CommandSender;
import me.Mastervrunner.Hello_World.Player;*/

public class Main extends JavaPlugin implements Listener{
	
ArrayList<String> inGame = new ArrayList<String>();
	
	boolean gameStarted = false;
	
	float roundTime;
	
	float timeMultiplier = 10;
	
	int numberOfRounds;
	
	Location spawnpoint;
	
	
	ArrayList<String> isIt = new ArrayList<String>();

	
	String compasBoi = "BlankExample123459";
	
	Location nearestOtherPlayer;
	
	int currentRound;
	
	String outPlayer;
	
	ArrayList<String> isOut = new ArrayList<String>();
	
	ArrayList<String> allJoinedGame = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		isIt.add("BlankExample123459");//Almost for got the ; 7:50 4/1/2020 yeetboi
		
		BukkitScheduler scheduler = getServer().getScheduler();
        
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if(gameStarted == true) {
            		
            		roundTime = roundTime - 1;
            		
            		if(roundTime >= 1) {
            			for(String p : inGame) {
            				if(isIt.get(0) != p) {
		            			String message = "You're not it, Run! Time left: " + roundTime;
		            	        Player player = Bukkit.getPlayer(p);
		            	        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            				} else if(isIt.get(0) == p) {
            					String message = "You're it! Tag someone! Time left: " + roundTime;
		            	        Player player = Bukkit.getPlayer(p);
		            	        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,  TextComponent.fromLegacyText(message));
            				}
            			}
            		}
            		
            		if(roundTime <= 0) {
            			if(numberOfRounds >= 1 && inGame.size() >= 2) {
            				
            				currentRound = currentRound + 1;
            				
            				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
	            				for(String player : isIt) {
	        						if(p.getName() == player) {
	        							p.sendMessage("You're out!");

	        							Bukkit.getPlayer(player).setGameMode(GameMode.SPECTATOR);
	        							inGame.remove(player);
	        							isOut.add(player);
	        						}
	        						outPlayer = isIt.get(0);
	        					}
            				}
            				
            				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            					for(String player : inGame) {
            						if(p.getName() == player) {
            							p.sendMessage(outPlayer + " has exploded!");
            							p.sendMessage("Round " + currentRound + " started! Run!");
            						}
            					}
            					
            				}
            				
            				roundStart();
            			}
            			if(inGame.size() == 1) {
            				gameEnd();
            				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            					gameStarted = false;
            				}
            			}
            		}
            		
            		if(roundTime <= 0) {
            			numberOfRounds = numberOfRounds - 1;
            			roundTime = (inGame.size()) * timeMultiplier;
            		}
            		
            	///	numberOfRounds = numberOfRounds - 1;//'  j///why just why is this here noob masterv 5:53 pm 4/1/2020
            		
            	}
            }
        }, 20L, 20L);
        
	}
	
	public void gameEnd() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.sendMessage(inGame.get(0) + " Won!");
			for(String player : inGame) {
				if(p.getName() == player) {
					p.teleport(spawnpoint);
					p.setGameMode(GameMode.SURVIVAL);
					
					if(p.getInventory().contains(new ItemStack(Material.TNT))) {
						p.getInventory().removeItem(new ItemStack(Material.TNT));
					}
					
					if(p.getInventory().contains(new ItemStack(Material.COMPASS))) {
						p.getInventory().removeItem(new ItemStack(Material.COMPASS));
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "minecraft:effect clear " + p.getName();
					Bukkit.dispatchCommand(console, command);
					
				}
			}
			for(String player2 : isOut) {
				if(p.getName() == player2) {
					p.teleport(spawnpoint);
					p.setGameMode(GameMode.SURVIVAL);
					
					if(p.getInventory().contains(new ItemStack(Material.TNT))) {
						p.getInventory().removeItem(new ItemStack(Material.TNT));
					}
					
					if(p.getInventory().contains(new ItemStack(Material.COMPASS))) {
						p.getInventory().removeItem(new ItemStack(Material.COMPASS));
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "minecraft:effect clear " + p.getName();
					Bukkit.dispatchCommand(console, command);
				}
			}
			
			//forgot to add those until i tested it lols 7:21 now 7:22 pm 4/1/2020
			
			for(String player2 : isIt) {
				if(p.getName() == player2) {
					p.teleport(spawnpoint);
					p.setGameMode(GameMode.SURVIVAL);
					
					if(p.getInventory().contains(new ItemStack(Material.TNT))) {
						p.getInventory().removeItem(new ItemStack(Material.TNT));
					}
					
					if(p.getInventory().contains(new ItemStack(Material.COMPASS))) {
						p.getInventory().removeItem(new ItemStack(Material.COMPASS));
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "minecraft:effect clear " + p.getName();
					Bukkit.dispatchCommand(console, command);
				}
			}
			
			for(String player2 : allJoinedGame) {
				if(p.getName() == player2) {
					p.teleport(spawnpoint);
					p.setGameMode(GameMode.SURVIVAL);
					
					if(p.getInventory().contains(new ItemStack(Material.TNT))) {
						p.getInventory().removeItem(new ItemStack(Material.TNT));
					}
					
					if(p.getInventory().contains(new ItemStack(Material.COMPASS))) {
						p.getInventory().removeItem(new ItemStack(Material.COMPASS));
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "minecraft:effect clear " + p.getName();
					Bukkit.dispatchCommand(console, command);
					
					//Bukkit.dispatchCommand(console, command2);//almost forgot to change to command2 yeets but up aovebe changed to yellows 7:39 pm 4/1/2020
				}
			}
			
		}
		
		inGame.clear();
		isOut.clear();
		
		isIt.clear();
		
		allJoinedGame.clear();
		
		isIt.add("BlankExample123459");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//hello
		
		int arglength;
		arglength = args.length;
		
		if (label.toLowerCase().equalsIgnoreCase("tnttag") && args.length >= 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args[0].equalsIgnoreCase("join")) {
					if(!inGame.contains(player.getName())) {
						inGame.add(player.getName());
						player.sendMessage(ChatColor.GOLD + "You joined TntTag!");
						allJoinedGame.add(player.getName());
						
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							p.sendMessage(ChatColor.BLUE + player.getName() + " has joined TntTag!");
						}
						
					} else {
						player.sendMessage(ChatColor.GOLD + "You have already joined TntTag!");//player.sendmap not sendmessa ge lol what does that do? : 7:20 pm 4/1/2020t
					}
				}
				
				if(args[0].equalsIgnoreCase("start")) {
					if(gameStarted == false) {
						//gameStarted == true;
						gameStarted = true;
						
						//Roundtim
						roundTime = (inGame.size()) * timeMultiplier;
						
						roundStart();
						
						numberOfRounds = inGame.size() - 1;
						
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							for(String player2 : inGame) {
								if(p.getName() == player2) {
									if(spawnpoint != null) {
										p.teleport(spawnpoint);
									} else {
										player.sendMessage(ChatColor.GOLD + "Spawnpoint not set!");
									}

								}
							}
						}
						
						player.sendMessage(ChatColor.GOLD + "Game started!");
						
						currentRound = 1;
						
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							for(String player3 : inGame) {
								if(p.getName() == player3) {
									p.setGameMode(GameMode.SURVIVAL);
								}
							}
						}
						
					} else {
						player.sendMessage(ChatColor.GOLD + "Game already started!");
					}
				}
				
				
				if(args[0].equalsIgnoreCase("createspawnpoint")) {
					
					spawnpoint = player.getLocation();
					
					player.sendMessage(ChatColor.GOLD + "You set the TntTag spawnpoint!");
				}
				
			//	1010pm 12 10:21 19 10:12 pm---
				
				if(args[0].toLowerCase() == "timemultiplier" && args[2] != null) {
					timeMultiplier = Float.parseFloat(args[2]); 
				}
				
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "You have to be a player to run this, lol");
				return true;
			}
			
		}
		return false;
		
	}
	
	public void roundStart() {
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			for(String player2 : inGame) {
				if(p.getName() == player2) {
					if(spawnpoint != null) {
						p.teleport(spawnpoint);
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "minecraft:effect clear " + p.getName();
					Bukkit.dispatchCommand(console, command);
				}
			}
		}

		int It = new Random().nextInt(inGame.size());
		
		It -= 1;
		
		Random random = new Random();
		
		String test = inGame.get(0);

		//For every player in inGame if their name is not in isIt, give them speed 1, else give them speed 2
		
		isIt.clear();
		isIt.add(test);
		
		for(String player : inGame) {
			for(String player2 : isIt) {
				if(player != player2) {
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(p.getName() == player) {
							for (PotionEffect effect : p.getActivePotionEffects()) {
								if(effect.getType() == PotionEffectType.SPEED) {
								p.removePotionEffect(effect.getType()); //removepassenger lol 6:36 pm 4/1/2020 thought mom was going to make me go into town :( but i convinced her not to make me go in because of the corona and i am busy with programming :)!!
								}
							}
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 1,true,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 3,true,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000, 3,true,false));


						}
					}
				} else {
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(p.getName() == player) {
							
							for (PotionEffect effect : p.getActivePotionEffects()) {
								if(effect.getType() == PotionEffectType.SPEED) {
								p.removePotionEffect(effect.getType());
								}
							}
							
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 3,true,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 3,true,false));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000, 3,true,false));//Just added saturation to both 10:00 now 10:01 am 4/2/2020
							
							p.getInventory().addItem(new ItemStack(Material.TNT,1));
							//Material was bad import btw; commented out on line 17 /// 3 slashes

						}
					}
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityHit(EntityDamageByEntityEvent event) {
		
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if(isIt.get(0) == player.getName() && inGame.contains(event.getEntity().getName())) {
				
				isIt.remove(0);
				isIt.clear();
				
				Player hitBoi = (Player) event.getEntity();
				isIt.add(hitBoi.getName());
				
				for (PotionEffect effect : hitBoi.getActivePotionEffects()) {
					if(effect.getType() == PotionEffectType.SPEED) {
						hitBoi.removePotionEffect(effect.getType());
					}
				}
				
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command = "minecraft:effect clear " + hitBoi.getName();
				Bukkit.dispatchCommand(console, command);
				
				hitBoi.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 4,true,false));
				
				hitBoi.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 3,true,false));
				hitBoi.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000, 3,true,false));
				
				hitBoi.getInventory().addItem(new ItemStack(Material.TNT,1));
				
				for (PotionEffect effect : player.getActivePotionEffects()) {
					if(effect.getType() == PotionEffectType.SPEED) {
						player.removePotionEffect(effect.getType());
					}
				}
				
				String command2 = "minecraft:effect clear " + player.getName();
				Bukkit.dispatchCommand(console, command2);
				
				
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 1,true,false));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 3,true,false));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000, 3,true,false));

				if(player.getInventory().contains(new ItemStack(Material.TNT))) {
					player.getInventory().removeItem(new ItemStack(Material.TNT));
				}
				
				if(player.getInventory().contains(new ItemStack(Material.COMPASS))) {
					player.getInventory().removeItem(new ItemStack(Material.COMPASS));
				}
				
				hitBoi.getInventory().addItem(new ItemStack(Material.COMPASS,1));
				//hitBoi.setCompassTarget(nearestOtherPlayer);
				
				compasBoi = hitBoi.getName();
				
			}
		}
	}
}
