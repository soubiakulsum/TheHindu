package com.example.thehinduclone.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thehinduclone.ui.menu.ElectionFragment;
import com.example.thehinduclone.ui.menu.HomeFragment;
import com.example.thehinduclone.ui.menu.BooksFragment;
import com.example.thehinduclone.ui.menu.BusinessFragment;
import com.example.thehinduclone.ui.menu.CitiesFragment;
import com.example.thehinduclone.ui.menu.EditorialsFragment;
import com.example.thehinduclone.ui.menu.EducationFragment;
import com.example.thehinduclone.ui.menu.EntertainmentFragment;
import com.example.thehinduclone.ui.menu.InternationalFragment;
import com.example.thehinduclone.ui.menu.LifeStyleFragment;
import com.example.thehinduclone.ui.menu.MultimediaFragment;
import com.example.thehinduclone.ui.menu.NationalFragment;
import com.example.thehinduclone.ui.menu.NewsInQuotesFragment;
import com.example.thehinduclone.ui.menu.ScienceFragment;
import com.example.thehinduclone.ui.menu.SpecialsFragment;
import com.example.thehinduclone.ui.menu.SportsFragment;
import com.example.thehinduclone.ui.menu.StatesFragment;
import com.example.thehinduclone.ui.menu.TechnologyFragment;
import com.example.thehinduclone.ui.menu.ThReadFragment;
import com.example.thehinduclone.ui.menu.TopPicksFragment;
import com.example.thehinduclone.ui.menu.WellbeingFragment;
import com.example.thehinduclone.ui.menu.CovidFragment;

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {

    public ViewPagerHomeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerHomeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return ElectionFragment.newInstance();
            case 2:
                return CovidFragment.newInstance();
            case 3:
                return NationalFragment.newInstance();
            case 4:
                return InternationalFragment.newInstance();
            case 5:
                return StatesFragment.newInstance();
            case 6:
                return BusinessFragment.newInstance();
            case 7:
                return EditorialsFragment.newInstance();
            case 8:
                return SportsFragment.newInstance();
            case 9:
                return EducationFragment.newInstance();
            case 10:
                return EntertainmentFragment.newInstance();
            case 11:
                return ScienceFragment.newInstance();
            case 12:
                return TechnologyFragment.newInstance();
            case 13:
                return MultimediaFragment.newInstance();
            case 14:
                return LifeStyleFragment.newInstance();
            case 15:
                return CitiesFragment.newInstance();
            case 16:
                return BooksFragment.newInstance();
            case 17:
                return NewsInQuotesFragment.newInstance();
            case 18:
                return WellbeingFragment.newInstance();
            case 19:
                return ThReadFragment.newInstance();
            case 20:
                return TopPicksFragment.newInstance();
            case 21:
                return SpecialsFragment.newInstance();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 22;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tabName = " ";
        switch (position) {
            case 0:
                tabName = "Home";
                break;
            case 1:
                tabName = "Elections";
                break;
            case 2:
                tabName = "COVID-19";
                break;
            case 3:
                tabName = "National";
                break;
            case 4:
                tabName = "International";
                break;
            case 5:
                tabName = "States";
                break;
            case 6:
                tabName = "Business";
                break;
            case 7:
                tabName = "Editorials & Opinion";
                break;
            case 8:
                tabName = "Sports";
                break;
            case 9:
                tabName = "Education";
                break;
            case 10:
                tabName = "Entertainment";
                break;
            case 11:
                tabName = "Science";
                break;
            case 12:
                tabName = "Technology";
                break;
            case 13:
                tabName = "Multimedia";
                break;
            case 14:
                tabName = "Life & Style";
                break;
            case 15:
                tabName = "Cities";
                break;
            case 16:
                tabName = "Books";
                break;
            case 17:
                tabName = "News In Quotes";
                break;
            case 18:
                tabName = "Wellbeing";
                break;
            case 19:
                tabName = "thREAD";
                break;
            case 20:
                tabName = "Top Picks";
                break;
            case 21:
                tabName = "Specials";
                break;
        }
        return tabName;
    }
}
