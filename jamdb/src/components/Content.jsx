import React,{ useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import apiContent from '../helpers/apiContent';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea,Box } from '@mui/material';
import '../scss/grid.scss';
import Grid from '@mui/material/Grid';
import GradeIcon from '@mui/icons-material/Grade';
import placeHolderImage from '../assets/placeholder.jpg';
import { Stack } from '@mui/system';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import { Link } from 'react-router-dom';
const Content = () => {
	const [anime, setAnime] = useState({});
	const [suggestions, setSuggestions] = useState([]);
	const param = useParams();
	const placeHolderDescription =
		'Doki doki chikara Ureshiii, doki doki Tomodachi arigatou damasu doko doki doki Baka Daijobu Senpai. Damasu Oppai Ureshiii chikara, Baka fuzakeru Otaku Tsundere Senpai damasu Tsundere Otaku fuzakeru. Arigatou nii san Kawaii okasan, Baka Tsundere doko chotto fuzakeru chotto.';
	useEffect(() => {
		const contentId = param.contentId;
		const getContent = async (contentId) => {
			await apiContent
				.get(`/content/show/${contentId}`)
				.then((data) => {
					setAnime(data.data);
				})
				.catch((error) => console.log(error));
			await apiContent
				.get(`/content/recommendation/${contentId}`)
				.then((data) => {
					setSuggestions(data.data);
				})
				.catch((error) => console.log(error));
		};
		getContent(contentId);
	}, [param]);
	const ShowSuggestions = () => (
		<List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
			{suggestions.slice(0, 5).map((suggestion) => (
				<ListItem alignItems="flex-start" key={suggestion.contentId}>
					<Link
						to={`/content/${suggestion.contentId}`}
						style={{ textDecoration: 'none', color: 'black' }}
					>
						<ListItemAvatar>
							<Avatar
								alt={suggestion.title}
								src={suggestion.thumbnail}
								onError={(e) => (e.target.src = placeHolderImage)}
							/>
						</ListItemAvatar>
						<ListItemText
							primary={suggestion.title}
							secondary={
								<React.Fragment>
									<Typography
										sx={{ display: 'inline' }}
										component="span"
										variant="body2"
										color="text.primary"
									>
										{suggestion.type}
									</Typography>
									{' - '}
									{suggestion.contentStatus}{' '}
									{
										<Box sx={{ display: 'flex', alignItems: 'center' }}>
											<GradeIcon sx={{ color: 'gold' }} /> {suggestion.score}
										</Box>
									}
								</React.Fragment>
							}
						/>
					</Link>
				</ListItem>
			))}
		</List>
	);
	return (
		<div className="grid-container">
			<Grid container spacing={2}>
				<Grid item xs={6} md={8}>
					<Card
						raised
						sx={{
							maxWidth: 700,
							maxHeight: 1000,
							padding: '0.1em',
						}}
					>
						<CardActionArea>
							<CardMedia
								component="img"
								image={anime.picture}
								onError={(e) => {
									e.target.src = placeHolderImage;
								}}
								alt={anime.title}
								sx={{ objectFit: 'cover', maxHeight: 500, maxWidth: 700 }}
							/>
							<CardContent>
								<Typography gutterBottom variant="h5" component="div">
									{anime.title}
								</Typography>
								<Typography variant="body2" color="text.secondary">
									{anime.description || placeHolderDescription}
								</Typography>
							</CardContent>
						</CardActionArea>
					</Card>
				</Grid>
				<Grid item xs={6} md={4}>
					<Stack spacing={2}>
						<Card raised sx={{ maxHeight: 1000, maxWidth: 300 }}>
							<CardContent>
								<Typography
									sx={{ fontSize: 14, color: '#1b4965', fontWeight: 400 }}
								>
									Format
								</Typography>
								<Typography
									sx={{ fontSize: 12, color: '#adb5bd', fontWeight: 200 }}
								>
									{anime.type}
								</Typography>
								<Typography
									sx={{ fontSize: 14, color: '#1b4965', fontWeight: 400 }}
								>
									Total Episodes
								</Typography>
								<Typography
									sx={{ fontSize: 12, color: '#adb5bd', fontWeight: 200 }}
								>
									{anime.totalEpisodes}
								</Typography>
								<Typography
									sx={{ fontSize: 14, color: '#1b4965', fontWeight: 400 }}
								>
									Status
								</Typography>
								<Typography
									sx={{ fontSize: 12, color: '#adb5bd', fontWeight: 200 }}
								>
									{anime.contentStatus}
								</Typography>
								<Typography
									sx={{ fontSize: 13, display: 'flex', alignItems: 'center' }}
								>
									<GradeIcon sx={{ color: 'gold' }} /> {anime.score}
								</Typography>
							</CardContent>
						</Card>
						<Card raised sx={{maxWidth:300}}>
							<CardContent>
								<Typography
									sx={{ fontSize: 20, color: '#1b4965', fontWeight: 500 }}
								>
									Related Content
								</Typography>
							</CardContent>
								<ShowSuggestions />
						</Card>
					</Stack>
				</Grid>
			</Grid>
		</div>
	);
};

export default Content;
